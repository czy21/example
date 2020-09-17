package com.team.portal.queue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "hello")
@Component
public class HelloReceiver {
    @RabbitHandler
    public void receive(String content) {
        System.out.println(content);
    }
}
