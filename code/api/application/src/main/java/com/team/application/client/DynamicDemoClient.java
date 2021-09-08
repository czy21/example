package com.team.application.client;

import feign.Headers;
import feign.RequestLine;

import java.util.Map;

public interface DynamicDemoClient {

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    Object find(Map<String, Object> param);
}
