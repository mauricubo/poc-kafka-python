import json
from confluent_kafka import Producer
from confluent_kafka.serialization import SerializationContext, MessageField
from confluent_kafka.schema_registry import SchemaRegistryClient
from confluent_kafka.schema_registry.avro import AvroSerializer
from message_generator import MessageGenerator
import os
import random
import time

MIN_TIME_BTW_MSGS = os.environ.get('MIN_TIME_BTW_MSGS', 2)
MAX_TIME_BTW_MSGS = os.environ.get('MAX_TIME_BTW_MSGS', 8)

kafka_server = os.environ.get('KAFKA_SERVER', "192.168.68.30:29093")
topic = os.environ.get("APP_TOPIC", 'pizza-orders')

def get_schema(fileName: str):
    schema_str = ""
    path = os.path.realpath(os.path.dirname(__file__))
    with open(f"{path}/schema/{fileName}") as f:
        schema_str = f.read()
    return schema_str

def main():
    # Configure the Kafka producer with Avro serialization
    producer_config = {
        'bootstrap.servers': kafka_server,
        'enable.idempotence': True,
        'acks': 'all',
        'max.in.flight.requests.per.connection': 5,
    }
    schema_registry_conf = {'url': "http://schemaregistry:8085"}
    
    # Create a Kafka producer instance
    producer = Producer(producer_config)
    schema_registry = SchemaRegistryClient(schema_registry_conf)
    
    key_serializer = AvroSerializer(schema_registry, get_schema("key.avsc"))
    value_serializer = AvroSerializer(schema_registry, get_schema("value.avsc"))
    msg_generator = MessageGenerator()
    
    try:
        print("Start producing and sending pizzas...")
        order_id = 1
        while True:
            # Generate the message
            message, msg_key = msg_generator.produce_msg(order_id)
            # we send the message to kafka
            producer.produce(topic,
                    key=key_serializer(msg_key, SerializationContext(topic, MessageField.KEY)),
                    value=value_serializer(message, SerializationContext(topic, MessageField.VALUE))
                    )
            producer.flush()
            order_id += 1
            # random sleep to be "more realistic"
            time.sleep(random.randint(int(MIN_TIME_BTW_MSGS), int(MAX_TIME_BTW_MSGS)))
    except KafkaException as e:
        print(f"Failed to produce message: {e}")
    except KeyboardInterrupt:
        print("Stop sending messages...")


if __name__ == "__main__":
    main()