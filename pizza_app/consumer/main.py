from flask import Flask, render_template
from flask_socketio import SocketIO
from confluent_kafka import Consumer
from confluent_kafka.serialization import SerializationContext, MessageField
from confluent_kafka.schema_registry import SchemaRegistryClient
from confluent_kafka.schema_registry.avro import AvroDeserializer
import json
import os

# OS Environment variables
kafka_server = os.environ.get('KAFKA_SERVER', '192.168.68.30:29093')
topic = os.environ.get("APP_TOPIC", 'pizza-orders')
group_id = os.environ.get("GROUP_IP", 'web')

def get_schema(fileName: str):
    schema_str = ""
    path = os.path.realpath(os.path.dirname(__file__))
    with open(f"{path}/schema/{fileName}") as f:
        schema_str = f.read()
    return schema_str

# Create a Flask app
app = Flask(__name__)
app.config['SECRET_KEY'] = os.environ.get('SECRET_KEY', 'ReallyS3cr3tK3yYouSh0uldCh4ng3!')

#Create a SocketIO instance
socketio = SocketIO(app)

sr_conf = {'url': "http://schemaregistry:8085"}
schema_registry = SchemaRegistryClient(sr_conf)

key_deserializer = AvroDeserializer(schema_registry, get_schema("key.avsc"))
value_pizza_orders_deserializer = AvroDeserializer(schema_registry, get_schema("value_pizza-orders.avsc"))
value_pizza_shop_revenue_deserializer = AvroDeserializer(schema_registry, get_schema("value_pizza-shop-revenue.avsc"))

# Create a Kafka Consumer (group id removed)
consumer_conf = {'bootstrap.servers': kafka_server,
                    'auto.offset.reset': "earliest",
                    'group.id': group_id}
consumer = Consumer(consumer_conf)

# Define a background task to consume messages from Kafka
def consume_kafka():
    consumer.subscribe([topic, "pizza-shop-revenue"])
    while True:
        try:
            msg = consumer.poll(1.0)
            if msg is None:
                continue
            
            if msg.value() is not None:
                print(msg.topic())
                if msg.topic() == "pizza-orders":
                    value = value_pizza_orders_deserializer(msg.value(), SerializationContext(msg.topic(), MessageField.VALUE))
                    socketio.emit('order', json.dumps(value, ensure_ascii=False))
                elif msg.topic() == "pizza-shop-revenue":
                    value = value_pizza_shop_revenue_deserializer(msg.value(), SerializationContext(msg.topic(), MessageField.VALUE))
                    socketio.emit('revenue', json.dumps(value, ensure_ascii=False))
            else:
                print("no value found")
                exit(1)
        except KeyboardInterrupt:
            break
    consumer.close()

# Start the background task
socketio.start_background_task(consume_kafka)

# Serve the index.html file
@app.route('/')
def index():
    return render_template('index.html')

# Start the server
if __name__ == "__main__":
    socketio.run(app, debug=True, host="0.0.0.0", port=3000, allow_unsafe_werkzeug=True)