package com.team.stream.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping(path = "example1")
public class Example1Controller {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping(path = "input1")
    public Map<String, Object> input1(@RequestParam("num") Integer num) {
        IntStream.range(0, num)
                .forEach(t -> {
                    rabbitTemplate.convertAndSend(String.join(".", "input1-in-0", "someGroup"), Integer.toString(t));
                });
        return Map.of();
    }
}
