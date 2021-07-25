package com.team.portal.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component
public class MessageConsumer {

    @KafkaListener(topics = {"topic1"})
    public void topic1Listener(Map<String, Object> data) {
        System.out.println("topic1 => " + data);
    }
}