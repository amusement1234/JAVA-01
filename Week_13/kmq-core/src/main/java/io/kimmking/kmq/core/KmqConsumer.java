package io.kimmking.kmq.core;

public class KmqConsumer<T> {

    private final KmqBroker broker;

    private Kmq kmq;

    private String name;

    public KmqConsumer(KmqBroker broker, String name) {
        this.broker = broker;
        this.name = name;
    }

    public void subscribe(String topic) {
        this.kmq = this.broker.findKmq(topic);
        if (null == kmq) {
            throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        }
    }

    public KmqMessage poll(String name) {
        return kmq.poll(name);
    }

}
