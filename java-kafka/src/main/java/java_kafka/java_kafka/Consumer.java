package java_kafka.java_kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {

        var consumer = new KafkaConsumer<String, String>(properties());

        consumer.subscribe(Collections.singletonList("consumo-cliente"));

        while (true) {
            var records = consumer.poll(java.time.Duration.ofMillis(100));
            records.forEach(record -> {
                System.out.println("Compra nova: ");
                System.out.println("Chave: " + record.key());
                System.out.println("Valor: " + record.value());
                System.out.println("Partição: " + record.partition());
                System.out.println("Offset: " + record.offset());
            });
        }

    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "consumo-cliente");
        return properties;
    }
}
