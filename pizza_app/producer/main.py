import json
from kafka import KafkaProducer
from message_generator import MessageGenerator
import os
import random
import time

MIN_TIME_BTW_MSGS = os.environ.get('MIN_TIME_BTW_MSGS', 2)
MAX_TIME_BTW_MSGS = os.environ.get('MAX_TIME_BTW_MSGS', 8)

kafka_server = os.environ.get('KAFKA_SERVER', "192.168.68.30:29093")
topic = os.environ.get("APP_TOPIC", 'pizza-orders')

producer = KafkaProducer(
    bootstrap_servers=kafka_server, 
    value_serializer=lambda v: json.dumps(v).encode('ascii'),
    key_serializer=lambda v: json.dumps(v).encode('ascii')
)

msg_generator = MessageGenerator()

def main():
    try:
        print("Start producing and sending pizzas...")
        order_id = 1
        while True:
            # Generate the message
            message, msg_key = msg_generator.produce_msg(order_id)
            # we send the message to kafka
            producer.send(topic,
                    key=msg_key,
                    value=message
                    )
            producer.flush()
            order_id += 1
            # random sleep to be "more realistic"
            time.sleep(random.randint(int(MIN_TIME_BTW_MSGS), int(MAX_TIME_BTW_MSGS)))
    except KeyboardInterrupt:
        print("Stop sending messages...")


if __name__ == "__main__":
    main()