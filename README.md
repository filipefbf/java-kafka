# java-kafka

Crating consumer and producer in java using kafka

To run the project, you need to have kafka installed in your machine. You can download kafka from [here](https://kafka.apache.org/downloads)

After downloading kafka, you need to start zookeeper and kafka server. You can do this by running the following commands:

```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```

After starting zookeeper and kafka server, you need to create a topic. You can do this by running the following command:

```bash
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
```

After creating the topic, you can run the producer and consumer classes.

To run the producer class, you can run the following command:

```bash
mvn exec:java -Dexec.mainClass="com.kafka.producer.Producer"
```

To run the consumer class, you can run the following command:

```bash
mvn exec:java -Dexec.mainClass="com.kafka.consumer.Consumer"
```

You can run the producer and consumer classes in different terminals.

You can also run the producer and consumer classes in different machines. You just need to change the `bootstrap.servers` property in the `Producer` and `Consumer` classes to the IP address of the machine where kafka server is running.

You can also run multiple instances of the consumer class. You just need to change the `group.id` property in the `Consumer` class.

You can also run multiple instances of the producer class. You just need to run the producer class multiple times.# java-kafka
