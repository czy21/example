package com.team.portal.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

//    @KafkaListener(topics = {"my-topic"})
////    @SendTo("confirm-topic")
//    public String consume(MessageEvent event) {
//        System.out.println("在consume方法中处理事件" + event);
//        return "接收到了:" + event.toString();
//    }


    @KafkaListener(topics = {"another-topic"})
    public void consumeAnother(ConsumerRecord<String,Long> event) {
        System.out.println("在consumeAnother方法中处理another-topic中的事件" + event);
    }
}