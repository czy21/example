package com.team.stream.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    RabbitTemplate rabbitTemplate;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    /*
      rabbit consumer => ack manual
     */
    @GetMapping(path = "input11")
    public Map<String, Object> input1(@RequestParam("num") Integer num) {
        sendBy(String.join(".", "input1Topic", "stream"), num, (t, n) -> rabbitTemplate.convertAndSend(t, n));
        return Map.of();
    }

    /*
        rabbit consumer batch
    */
    @GetMapping(path = "input12")
    public Map<String, Object> input2(@RequestParam("num") Integer num) {
        sendBy(String.join(".", "input2Topic", "stream"), num, (t, n) -> rabbitTemplate.convertAndSend(t, n));
        return Map.of();
    }

    /*
       kafka consumer => auto
     */
    @GetMapping(path = "input21")
    public Map<String, Object> input3(@RequestParam("num") Integer num) {
        sendBy("input3Topic", num, (t, n) -> kafkaTemplate.send(t, n));
        return Map.of();
    }

    @GetMapping(path = "input22")
    public Map<String, Object> input4(@RequestParam("num") Integer num) {
        sendBy("input4Topic", num, (t, n) -> kafkaTemplate.send(t, n));
        return Map.of();
    }

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(path = "input31")
    public Map<String, Object> input5(@RequestParam("num") Integer num) {
        sendBy("input5Topic", num, (t, n) -> {
            try {
                kafkaTemplate.send(t, objectMapper.writeValueAsString(Map.of("value", Integer.parseInt(n))));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return Map.of();
    }

    private void sendBy(String topic, Integer num, BiConsumer<String, String> templateConsumer) {
        IntStream.range(0, num).forEach(t -> templateConsumer.accept(topic, Integer.toString(t)));
    }
}
