package com.aldisued;

import com.aldisued.avro.Pizza;
import com.aldisued.avro.PizzaOrder;
import com.aldisued.avro.PizzaOrderShop;
import com.aldisued.avro.PizzaShopRevenue;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;

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
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "pizza-shop-revenue");
        streamsConfiguration.put(StreamsConfig.CLIENT_ID_CONFIG, "pizza-shop-revenue-client");
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        streamsConfiguration.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        streamsConfiguration.put("schema.registry.url", schemaRegistryUrl);
        streamsConfiguration.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        streamsConfiguration.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 2 * 1000);
        
        final Map<String, String> serdeConfig = Collections.singletonMap("schema.registry.url", schemaRegistryUrl);

        final Serde<PizzaOrderShop> keySpecificAvroSerde = new SpecificAvroSerde<>();
        keySpecificAvroSerde.configure(serdeConfig, true); // `true` for record keys
        final Serde<PizzaShopRevenue> valueSpecificAvroSerde = new SpecificAvroSerde<>();
        valueSpecificAvroSerde.configure(serdeConfig, false); // `false` for record values

        final StreamsBuilder builder = new StreamsBuilder();
        final KStream<PizzaOrderShop, PizzaOrder> shopRevenue = builder.stream(inputTopic);
        final KTable<PizzaOrderShop, PizzaShopRevenue> aggregated = shopRevenue
            .groupByKey()
            .aggregate(
                () -> PizzaShopRevenue.newBuilder().setShop("PLACEHOLDER").setRevenue(0).build(), 
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
                Materialized.with(keySpecificAvroSerde, valueSpecificAvroSerde)
            );
        aggregated.toStream().to(outputTopic);
        return new KafkaStreams(builder.build(), streamsConfiguration);
    }
}
