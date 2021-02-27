package com.team.portal.kafka;

import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

//    @KafkaListener(topics = {"my-topic"})
//    @SendTo("confirm-topic")
//    public void consume(ConsumerRecord<String, String> event) {
//        System.out.println("consume => " + event);
//    }


//    @KafkaListener(topics = {"another-topic"}, properties = {
//            "key.deserializer=org.apache.kafka.common.serialization.StringDeserializer",
//            "value.deserializer=org.apache.kafka.common.serialization.LongDeserializer"
//    })
//    public void consumeAnother(ConsumerRecord<String, String> event) {
//        System.out.println("another-topic => " + event);
//    }
}