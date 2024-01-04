# poc-kafka-python

This project is a proof of concept for using Kafka with Python. It demonstrates how to produce and consume messages from a Kafka cluster using the kafka-Python library.

## Installation and usage
To run this project, you need to have `docker` and `docker compose` on your system.


``` bash
$ docker compose up -d
```

## Services
This project has services related to the kafka cluster and service related to the consumer and producer.

- **kafka-ui**: Web interface to interact with the cluster 
- **zookeeper**: Kafka cluster controler, keeps track of status of the Kafka cluster nodes and it also keeps track of Kafka topics, partitions etc.
- **kafka**: Is the kafka broker, this example only has one broker but it is posible to extend it.
- **schemaregistry**: Manage the schema of the cluster.
- **app_producer**: App based on [aiven-labs](https://github.com/Aiven-Labs/python-fake-data-producer-for-apache-kafka/blob/main/pizzaproducer.py) example and adapted for this project.
- **app_consumer**: Flask app with SocketIO to stream in a webpage all the orders consumed from kafka.

## URLs and access
### Kafka-ui
`http://<your_host_machine>:8080/`

### app_consumer
`http://<your_host_machine>:3000/`

---

**Notes:** If external access to your kafka (outside docker host) is need it, change the `KAFKA_ADVERTISED_LISTENERS`, the `EXTERNAL_DIFFERENT_HOST://192.168.68.30:29093` parameter to the DNS name or the external IP address assigned to the host machine.

