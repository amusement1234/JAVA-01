package io.kimmking.kmq.core;

import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Kmq {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.offset = 0;
        this.queue = new MessageQueue(capacity);
        this.offsetMap = new ConcurrentHashMap<>();
    }

    private String topic;

    private int capacity;

    private int offset;

    private MessageQueue queue;

    private Map<String, Integer> offsetMap;

    public void send(KmqMessage message) {
        queue.write(message, this.offset++);
    }

    @SneakyThrows
    public KmqMessage poll(String consumerName) {
        offsetMap.putIfAbsent(consumerName, 1);
        Integer offset = offsetMap.get(consumerName);
        if (offset > this.offset) {
            return null;
        }
        offsetMap.computeIfPresent(consumerName, (k, v) -> v++);

        return queue.read(offset);
    }

}
