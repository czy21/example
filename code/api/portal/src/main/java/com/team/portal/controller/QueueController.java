package com.team.portal.controller;

import com.czy.learning.web.controller.BaseController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamInfo;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "queue")
@RestController
public class QueueController extends BaseController {

    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ObjectMapper objectMapper;
//    @Autowired
//    PulsarTemplate<Map<String, Object>> pulsarTemplate;


    @PostMapping(path = "redis/stream/push1")
    public Map<String, Object> redisQueuePush(@RequestBody Map<String, String> param) {
        redisTemplate.opsForStream().add(StreamRecords.newRecord().in("log-api").ofMap(param));
        return Map.of();
    }

    @GetMapping(path = "redis/stream/info")
    public StreamInfo.XInfoStream redisStreamCount(@RequestParam String key) {
        return redisTemplate.opsForStream().info(key);
    }

    @GetMapping(path = "redis/stream/recreate")
    public Map<String, Object> redisStreamPrune(@RequestParam String key) {
        redisTemplate.delete(key);
        redisTemplate.opsForStream().createGroup(key, ReadOffset.from("0-0"), "log-group");
        return Map.of();
    }


    @PostMapping(path = "redis/push2")
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
