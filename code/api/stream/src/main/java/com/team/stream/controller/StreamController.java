package com.team.stream.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping(path = "stream")
public class StreamController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping(path = "input1")
    public Map<String, Object> input1(@RequestParam("num") Integer num) {
        IntStream.range(0, num)
                .forEach(t -> {
                    rabbitTemplate.convertAndSend(String.join(".", "input1Topic", "stream"), Integer.toString(t));
                });
        return Map.of();
    }

    @GetMapping(path = "input2")
    public Map<String, Object> input2(@RequestParam("num") Integer num) {
        IntStream.range(0, num)
                .forEach(t -> {
                    rabbitTemplate.convertAndSend(String.join(".", "input2Topic", "stream"), Integer.toString(t));
                });
        return Map.of();
    }
}
