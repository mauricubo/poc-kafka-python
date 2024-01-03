from flask import Flask, render_template
from flask_socketio import SocketIO
from kafka import KafkaConsumer
import os

# OS Environment variables
kafka_server = os.environ.get('KAFKA_SERVER', "192.168.68.30:29093")
topic = os.environ.get("APP_TOPIC", 'pizza-orders')

# Create a Flask app
app = Flask(__name__)
app.config['SECRET_KEY'] = os.environ.get('SECRET_KEY', 'ReallyS3cr3tK3yYouSh0uldCh4ng3!')

#Create a SocketIO instance
socketio = SocketIO(app)

# Create a Kafka Consumer
consumer = KafkaConsumer(topic,
                         bootstrap_servers=kafka_server, # This should be a env variable
                         auto_offset_reset= 'latest',
                         enable_auto_commit= True 
                        )

# Define a background task to consume messages from Kafka
def consume_kafka():
    for message in consumer:
        # Emit the message to the socket
        #print(message.value)
        socketio.emit('message', message.value.decode('ascii'))

# Start the background task
socketio.start_background_task(consume_kafka)

# Serve the index.html file
@app.route('/')
def index():
    return render_template('index.html')

# Start the server
if __name__ == "__main__":
    socketio.run(app, debug=True, host="0.0.0.0", port=3000, allow_unsafe_werkzeug=True)