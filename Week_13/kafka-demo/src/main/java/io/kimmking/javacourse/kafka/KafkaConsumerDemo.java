package io.kimmking.javacourse.kafka;

import io.kimmking.javacourse.kafka.kimmking.ConsumerImpl;
import io.kimmking.javacourse.kafka.kimmking.ProducerImpl;

public class KafkaConsumerDemo {

    public static void main(String[] args) {
        testConsumer();
    }

    private static void testConsumer() {

        ConsumerImpl consumer = new ConsumerImpl();
        consumer.consumeOrder();

    }
}
