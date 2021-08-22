package com.team.portal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.cooperated.controller.BaseController;
import lombok.SneakyThrows;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "es")
public class ESController extends BaseController {

    @Autowired
    RestHighLevelClient highLevelClient;

    @Autowired
    ObjectMapper objectMapper;

    @SneakyThrows
    @PostMapping(path = "put")
    public Map<String, Object> putObj() {
        IndexRequest request = new IndexRequest("posts");
        request.id(UUID.randomUUID().toString());
        String jsonString = objectMapper.writeValueAsString(Map.of("name", "陈昭宇"));
        request.source(jsonString, XContentType.JSON);
        highLevelClient.index(request, RequestOptions.DEFAULT);
        return Map.of("status", "success");
    }
}
