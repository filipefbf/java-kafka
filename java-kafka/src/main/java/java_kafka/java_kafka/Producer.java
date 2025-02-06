package java_kafka.java_kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Producer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        var producer = new KafkaProducer<String, String>(properties());

        var record = new ProducerRecord<String, String>("ecommerce.compras", "cliente-1", "compras:50reais");

        Callback callback = (data, error) -> {
            if (error != null) {
                error.printStackTrace();
                return;
            }
            System.out.println("Mensagem enviada com sucesso: ");
            System.out.println("Tópico: " + data.topic());
            System.out.println("Partição: " + data.partition());
            System.out.println("Offset: " + data.offset());
            System.out.println("Timestamp: " + data.timestamp());
        };

        for (int i = 0; i < 10; i++) {
            var record2 = new ProducerRecord<String, String>("ecommerce.compras",
                    "cliente-" + "compras" + i, "reais");
            producer.send(record2, callback).get();
        }

    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }

}
