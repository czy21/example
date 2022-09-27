package com.team.stream.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping(path = "example1")
public class Example1Controller {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("input1")
    public Map<String, Object> input1(@RequestParam("num") Integer num) {
        IntStream.range(0, num)
                .forEach(t -> {
//                    rabbitTemplate.convertAndSend();
                });
        return Map.of();
    }
}
