package com.team.edge.config;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

import static com.team.edge.config.EdgeQueueConfig.GH_DEVICE_ALERT_EXCHANGE;

@Component
public class EdgeConsumerConfig {

    @Autowired
    Environment environment;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(), exchange = @Exchange(name = GH_DEVICE_ALERT_EXCHANGE, type = ExchangeTypes.FANOUT)), ackMode = "AUTO")
    public void receive(String message) {
        Integer port = environment.getProperty("server.port", Integer.class);
        System.out.println(MessageFormat.format("port: {0},msg: {1}", port, message));
    }
}
