package com.team.gateway.config;

import com.team.gateway.utils.PathUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.webflux.ui.SwaggerConfigResource;
import org.springdoc.webflux.ui.SwaggerWelcomeCommon;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.*;
import java.util.stream.Collectors;

import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springframework.util.StringUtils;

@Slf4j
public class CustomSwaggerConfigResource extends SwaggerConfigResource {

    SwaggerWelcomeCommon swaggerWelcomeCommon;
    GatewayProperties gatewayProperties;

    public CustomSwaggerConfigResource(SwaggerWelcomeCommon swaggerWelcomeCommon,
                                       SwaggerUiConfigParameters swaggerUiConfigParameters,
                                       GatewayProperties gatewayProperties) {
        super(swaggerWelcomeCommon);
        this.swaggerWelcomeCommon = swaggerWelcomeCommon;
        this.gatewayProperties = gatewayProperties;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getSwaggerUiConfig(ServerHttpRequest request) {
        Map<String, Object> resp = super.getSwaggerUiConfig(request);
        if (CollectionUtils.isEmpty((Collection<SwaggerUrl>) resp.get(SwaggerUiConfigParameters.URLS_PROPERTY))){
            resp.put(SwaggerUiConfigParameters.URLS_PROPERTY,new LinkedHashSet<>());
        }
        LinkedHashSet<SwaggerUrl> urls = (LinkedHashSet<SwaggerUrl>) resp.get(SwaggerUiConfigParameters.URLS_PROPERTY);
        String contextPath = request.getPath().contextPath().value();
        if (!StringUtils.hasLength(contextPath)) {
            // 从header中获取
            List<String> referer = request.getHeaders().get("Referer");
            if (referer != null && !referer.isEmpty()) {
                String value = referer.get(0);
                contextPath = PathUtils.getContextPath(value);
            } else {
                contextPath = "/";
            }
        }
        log.info("contextPath: {}", contextPath);
        Set<String> existsNames = urls.stream().map(SwaggerUrl::getName).collect(Collectors.toSet());
        List<RouteDefinition> routes = Optional.ofNullable(gatewayProperties.getRoutes()).orElse(new ArrayList<>());
        for (RouteDefinition r : routes) {
            SwaggerUrl url = new SwaggerUrl();
            url.setName(r.getId());
            url.setUrl(contextPath + "/" + r.getId() + "/v3/api-docs");
            url.setDisplayName(r.getId());
            if (!existsNames.contains(url.getName())) {
                urls.add(url);
            }
        }

        return resp;
    }
}
