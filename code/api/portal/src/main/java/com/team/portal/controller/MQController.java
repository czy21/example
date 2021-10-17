package com.team.portal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.application.config.QueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.Reference;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping(path = "mq")
public class MQController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    /*
    127.0.0.1:8080/mq/rabbitTest1?start=1&end=1000
     */
    @GetMapping(path = "rabbitTest1")
    public Map<String, Object> rabbitTest1(@RequestParam("start") Integer start, @RequestParam("end") Integer end) throws JsonProcessingException {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        log.info(StringUtils.join(List.of("==========", start), " "));
        IntStream.rangeClosed(start, end).forEach(t -> {
            rabbitTemplate.convertAndSend(QueueConfig.ThroughputTest1_TOPIC, Map.of("id", t));
        });
        log.info(StringUtils.join(List.of("==========", end), " "));
        return Map.of("start", start, "end", end);
    }

    @GetMapping(path = "kafkaTest1")
    public Map<String, Object> kafkaTest1(@RequestParam("start") Integer start, @RequestParam("end") Integer end) {
        log.info(StringUtils.join(List.of("==========", start), " "));
        IntStream.rangeClosed(start, end).forEach(t -> kafkaTemplate.send(QueueConfig.SPI_FILE_TOPIC, Map.of("name",t)));
        log.info(StringUtils.join(List.of("==========", end), " "));
        return Map.of("start", start, "end", end);
    }

}
