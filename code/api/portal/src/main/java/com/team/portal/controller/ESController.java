package com.team.portal.controller;

import com.team.cooperated.controller.BaseController;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(path = "es")
public class ESController extends BaseController {

    @Autowired
    RestHighLevelClient highLevelClient;

    @PostMapping(path = "put")
    public Map<String, Object> putObj() throws IOException {
        GetRequest getRequest = new GetRequest("post", "customer");
        var p = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        return Map.of("status", "success");
    }
}
