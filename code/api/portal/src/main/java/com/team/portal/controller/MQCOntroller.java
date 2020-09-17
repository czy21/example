package com.team.portal.controller;


import com.team.cooperated.controller.BaseController;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "mqExample")
public class MQCOntroller extends BaseController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @PostMapping(path = "send")
    public Map<String, Object> send() {
        rabbitTemplate.convertAndSend(queue.getName(), "你好我的世界");
        return Map.of("status", "send");
    }

    @PostMapping(path = "receiver")
    public Map<String, Object> receiver() {
        return Map.of("status", "receiver");
    }

}
