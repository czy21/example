package com.team.portal.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.application.model.vo.FileVO;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.cooperated.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RequestMapping(path = "sale")
@RestController
@Slf4j
public class SaleController extends BaseController {

    @Autowired
    MaterialService materialService;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier("fileResolve")
    Queue queue;
    @Autowired
    ObjectMapper objectMapper;


    @Autowired
    RedisTemplate<String, Map<String, Object>> stringMapRedisTemplate;

    @PostMapping(path = "upload")
    public MaterialVO upload(FileVO fileVO) throws IOException {
        MaterialVO materialVO = materialService.upload(fileVO, "DEFAULT");
        rabbitTemplate.convertAndSend(queue.getName(), JSONObject.toJSONString(materialVO));
        return materialVO;
    }

    @PostMapping(path = "redisTest")
    public Map<String, Object> redisTest() {
        return Map.of();
    }

}
