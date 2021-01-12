package com.team.portal.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedRate = 2000)
    public void send() {
        kafkaTemplate.send("my-topic", "czy");
//        kafkaTemplate.send("my-topic", "zjy", (long) 6);
    }
}
