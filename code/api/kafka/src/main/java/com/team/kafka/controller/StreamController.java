package com.team.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

@RestController
@RequestMapping(path = "stream")
public class StreamController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    /*
       kafka consumer => auto
     */
    @GetMapping(path = "input3")
    public Map<String, Object> input3(@RequestParam("num") Integer num) {
        sendBy("input4Topic", num, (t, n) -> kafkaTemplate.send(t, n));
        return Map.of();
    }

    private void sendBy(String topic, Integer num, BiConsumer<String, String> templateConsumer) {
        IntStream.range(0, num).forEach(t -> templateConsumer.accept(topic, Integer.toString(t)));
    }
}
