package com.team.gateway.controller;



import com.team.gateway.utils.PathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.*;

@Slf4j
@RestController
public class OpenAPIController {
    GatewayProperties gatewayProperties;

    public OpenAPIController(GatewayProperties gatewayProperties) {
        this.gatewayProperties = gatewayProperties;
    }

    @GetMapping({"/v3/api-docs/swagger-config"})
    public Mono<ResponseEntity<Map<String, Object>>> swaggerConfig(ServerHttpRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("configUrl", "/v3/api-docs/swagger-config");
        String contextPath = request.getPath().contextPath().value();
        if (!StringUtils.hasLength(contextPath)) {
            List<String> referer = request.getHeaders().get("Referer");
            if (referer != null && !referer.isEmpty()) {
                String value = referer.get(0);
                log.debug("Referer:{}", value);
                contextPath = PathUtils.getContextPath(value);
            } else {
                contextPath = "/";
            }
        }
        log.info("contextPath: {}", contextPath);
        List<Object> sortedSet = new LinkedList<>();
        List<RouteDefinition> routes = Optional.ofNullable(gatewayProperties.getRoutes()).orElse(new ArrayList<>());
        for (RouteDefinition r : routes) {
            Map<String, Object> t = new HashMap<>();
            t.put("url", contextPath + "/" + r.getId() + "/v3/api-docs");
            t.put("contextPath", contextPath + "/" + r.getId());
            t.putAll(r.getMetadata());
            t.putIfAbsent("name", r.getId());
            sortedSet.add(t);
        }
        response.put("urls", sortedSet);
        return Mono.just(ResponseEntity.ok().body(response));
    }
}