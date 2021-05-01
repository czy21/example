package com.team.portal.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = {"topic1"},properties = {
            "key.deserializer=org.apache.kafka.common.serialization.StringDeserializer",
            "value.deserializer=org.apache.kafka.common.serialization.StringDeserializer"
    })
    public void topic1Listener(ConsumerRecord<String, String> event) {
        System.out.println("topic1 => " + event);
    }
}