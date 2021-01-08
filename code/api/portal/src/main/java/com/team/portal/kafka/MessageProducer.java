package com.team.portal.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageProducer {

    KafkaTemplate<Object, Object> kafkaTemplate;

    public MessageProducer(KafkaTemplate<Object, Object> kafkaTemplate) { //1
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 2000)
    public void send() {
        kafkaTemplate.send("my-topic", new MessageEvent(UUID.randomUUID().toString(), "wyf"));
    }
}
