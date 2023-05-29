package com.team.edge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.edge.config.EdgeQueueConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.IntStream;

@RequestMapping(path = "edge")
@RestController
public class EdgeController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping(path = "publish")
    public void publish(@RequestBody Map<String, Object> param) {
        IntStream.rangeClosed(1, (Integer) param.get("size")).forEach(t -> rabbitTemplate.convertAndSend(EdgeQueueConfig.GH_DEVICE_ALERT_EXCHANGE, "", Map.of("i", t)));
    }
}
