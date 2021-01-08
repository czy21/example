package com.team.portal.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = {"my-topic"}) //1
//    @SendTo("confirm-topic") //2
    public String consume(MessageEvent event) {
        System.out.println("在consume方法中处理事件" + event);
        return "接收到了:" + event.toString();
    }
}