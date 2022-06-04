package com.team.portal.listener;

import com.czy.learning.pulsar.annotation.PulsarListener;
import com.czy.learning.pulsar.core.ProducerBuilderWrapper;
import com.czy.learning.pulsar.core.PulsarTemplate;
import com.team.application.config.QueueConfig;
import com.team.application.model.RowModel;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PulsarConsumer {

    @Autowired
    PulsarTemplate pulsarTemplate;
    @Autowired
    PulsarClient pulsarClient;

    @PulsarListener(topics = QueueConfig.TOPIC_A, subscriptionName = "hello", clazz = Map.class)
    public void topicA(Map<String, Object> record) {
        System.out.println(record);
    }

    @PulsarListener(topics = QueueConfig.TOPIC_B, subscriptionName = "hello", clazz = Map.class)
    public void topicB(Map<String, Object> record) {
        System.out.println(record);
    }
}