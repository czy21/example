package com.czy.pulsar.producer;

import org.apache.pulsar.client.api.ProducerBuilder;

public class Producer<T> {
    public ProducerBuilder<T> builder;

    public static <T> ProducerBuilder<T> builder() {
        return new Producer<T>().builder;
    }



}
