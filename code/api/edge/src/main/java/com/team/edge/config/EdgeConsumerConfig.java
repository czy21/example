package com.team.edge.config;

import com.team.edge.AlertSocketHandler;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

import static com.team.edge.config.EdgeQueueConfig.GH_DEVICE_ALERT_EXCHANGE;

@Component
public class EdgeConsumerConfig {

    @Autowired
    Environment environment;
    @Autowired
    AlertSocketHandler alertSocketHandler;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(), exchange = @Exchange(name = GH_DEVICE_ALERT_EXCHANGE, type = ExchangeTypes.FANOUT)), ackMode = "AUTO")
    public void receive(String message) {
        simpMessagingTemplate.convertAndSend("/topic/alert", message);
//        alertSocketHandler.notifyMessage(message);
    }
}
