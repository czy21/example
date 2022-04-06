package com.czy.pulsar.core;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;

import java.util.Map;

public class PulsarFactory {
    PulsarClient client;
    Map<String, Producer<?>> producerMap;
    Map<String, Consumer<?>> consumerMap;

    public PulsarClient getClient() {
        return client;
    }

    public void setClient(PulsarClient client) {
        this.client = client;
    }

    public Map<String, Producer<?>> getProducerMap() {
        return producerMap;
    }

    public void setProducerMap(Map<String, Producer<?>> producerMap) {
        this.producerMap = producerMap;
    }

    public Map<String, Consumer<?>> getConsumerMap() {
        return consumerMap;
    }

    public void setConsumerMap(Map<String, Consumer<?>> consumerMap) {
        this.consumerMap = consumerMap;
    }
}
