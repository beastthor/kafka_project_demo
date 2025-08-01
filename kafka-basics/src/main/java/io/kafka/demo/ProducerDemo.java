package io.kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {

    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main(String[] args) {
        log.info("I am a Kafka Producer!");

        // connect to localhost
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");

        //Connect to cloud cluster
//        properties.setProperty("bootstrap.servers","pkc-4ygn6.us-east-2.aws.confluent.cloud:9092");
//        properties.setProperty("security.protocol", "SASL_SSL");
//        properties.setProperty("sasl.mechanism", "PLAIN");


        // set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());



        // create Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // create a Producer Record
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("demo_java", "hello world");

        // Send Data
        producer.send(producerRecord);

        // tell the producer to send all data and block until done -- synchronous

        producer.flush();

        // flush and close the producer
        producer.close();
    }
}
