package com.team.cooperated.interceptor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class SimpleInterceptor implements HandlerInterceptor {

    private String applicationName;
    private ObjectMapper objectMapper;

    public SimpleInterceptor(String applicationName, ObjectMapper objectMapper) {
        this.applicationName = applicationName;
        this.objectMapper = objectMapper.copy();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("domain", applicationName);
        return true;
    }
}
