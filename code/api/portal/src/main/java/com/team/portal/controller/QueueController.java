package com.team.portal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import com.team.cooperated.controller.BaseController;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping(path = "queue")
@RestController
public class QueueController extends BaseController {

    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ObjectMapper objectMapper;
//    @Autowired
//    PulsarTemplate<Map<String, Object>> pulsarTemplate;


    @PostMapping(path = "redisPush1")
    public Map<String, Object> redisQueuePush(@RequestBody Map<String, String> param) {
        redisTemplate.opsForStream().add(StreamRecords.newRecord().in("log-api").ofMap(param));
        return Map.of();
    }

    @GetMapping(path = "redisStream/info")
    public StreamInfo.XInfoStream redisStreamCount(@RequestParam String key) {
        return redisTemplate.opsForStream().info(key);
    }

    @GetMapping(path = "redisStream/delete")
    public Map<String, Object> redisStreamPrune(@RequestParam String key) {
        redisTemplate.delete(key);
        return Map.of();
    }

    @GetMapping(path = "redisStreamCreate")
    public Map<String, Object> redisStreamCreate(@RequestParam String key) {
        redisTemplate.opsForStream().createGroup("log-api", ReadOffset.from("0-0"), "log-group");
        return Map.of();
    }


    @PostMapping(path = "redisPush2")
    public Map<String, Object> redisQueuePush2(@RequestBody Map<String, Object> param) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(param);
        redisTemplate.opsForList().leftPush("kf:log:token2", json);
        return Map.of();
    }

    @PostMapping(path = "pulsarPush1")
    public Map<String, Object> pulsarPush1(@RequestBody Map<String, Object> param) throws PulsarClientException {
//        pulsarTemplate.send(QueueConfig.DEMO_TOPIC_1, param);
        return Map.of();
    }

}
