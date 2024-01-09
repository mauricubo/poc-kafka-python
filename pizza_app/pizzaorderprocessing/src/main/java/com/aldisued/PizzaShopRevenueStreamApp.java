package com.aldisued;

import com.aldisued.avro.Pizza;
import com.aldisued.avro.PizzaOrder;
import com.aldisued.avro.PizzaOrderShop;
import com.aldisued.avro.PizzaShopRevenue;

import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes.IntegerSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Repartitioned;
import org.apache.kafka.streams.kstream.SessionWindows;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;

public class PizzaShopRevenueStreamApp 
{
    public static void main( String[] args )
    {
        System.out.println(getEnv("SCHEMA_REGISTRY_URL"));
        final KafkaStreams streams = buildPizzaShotRevenue(
            getEnv("KAFKA_SERVER"), getEnv("SCHEMA_REGISTRY_URL") , getEnv("APP_TOPIC") , "pizza-shop-revenue");
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

    static String getEnv(String k) {
        return System.getenv(k);
    }
    
    static KafkaStreams buildPizzaShotRevenue(final String bootstrapServers,
                                              final String schemaRegistryUrl,
                                              final String inputTopic,
                                              final String outputTopic) {
        final Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "pizza-shop");
        streamsConfiguration.put(StreamsConfig.CLIENT_ID_CONFIG, "pizza-shop-revenue-client");
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        streamsConfiguration.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, IntegerSerde.class);
        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        streamsConfiguration.put("schema.registry.url", schemaRegistryUrl);
        streamsConfiguration.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        streamsConfiguration.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 2 * 1000);
        
        final Map<String, String> serdeConfig = Collections.singletonMap("schema.registry.url", schemaRegistryUrl);

        final Serde<PizzaOrder> inputValueSpecificAvroSerde = new SpecificAvroSerde<>();
        inputValueSpecificAvroSerde.configure(serdeConfig, false);
        final Serde<PizzaOrderShop> keySpecificAvroSerde = new SpecificAvroSerde<>();
        keySpecificAvroSerde.configure(serdeConfig, true); // `true` for record keys
        final Serde<PizzaShopRevenue> valueSpecificAvroSerde = new SpecificAvroSerde<>();
        valueSpecificAvroSerde.configure(serdeConfig, false); // `false` for record values

        /** Start: dedup */
        final StreamsBuilder builder = new StreamsBuilder();
        final KStream<PizzaOrderShop, PizzaOrder> input = builder.stream(inputTopic, Consumed.with(keySpecificAvroSerde, inputValueSpecificAvroSerde));
        Duration gracePeriod = Duration.ofSeconds(2);
        Duration dedupWindowSize = gracePeriod.multipliedBy(2);

        KStream<PizzaOrderShop, PizzaOrder> deduped = input
            .selectKey((k,v) -> v.getId())
            .groupByKey()
            .windowedBy(SessionWindows.ofInactivityGapAndGrace(dedupWindowSize, gracePeriod))
            .reduce(
                (value1, value2) -> value2,
                Materialized
                    .with(new IntegerSerde(), inputValueSpecificAvroSerde)
            )
           .toStream()
           .map(
                (wk, v) -> KeyValue
                            .pair(PizzaOrderShop.newBuilder().setShop(v.getShop()).build(), v)
            )
            .repartition(
                Repartitioned
                   .with(keySpecificAvroSerde, inputValueSpecificAvroSerde)
            );
        /** End: dedup */

        /** Start: revenue calculation */
        final KTable<PizzaOrderShop, PizzaShopRevenue> aggregated = deduped
            .groupByKey()
            .aggregate(
                () -> PizzaShopRevenue
                        .newBuilder()
                        .setShop("PLACEHOLDER")
                        .setRevenue(0)
                        .build(), 
                (k, v, state) -> {
                    int totalPriceOfOrder = 0;
                    Iterator<Pizza> iter = v.getPizzas().iterator();
                    while(iter.hasNext()) {
                        Pizza pizza = iter.next();
                        totalPriceOfOrder += pizza.getPrice();
                    }
                    return PizzaShopRevenue.newBuilder()
                        .setShop(k.getShop())
                        .setRevenue(state.getRevenue() + totalPriceOfOrder)
                        .build();
                },
                Materialized
                    .with(keySpecificAvroSerde, valueSpecificAvroSerde)
            );
        aggregated.toStream().to(outputTopic);
        /** End: revenue calculation */
        return new KafkaStreams(builder.build(), streamsConfiguration);
    }
}
