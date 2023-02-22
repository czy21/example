package com.team.gateway.config;

import io.github.mweirauch.micrometer.jvm.extras.ProcessMemoryMetrics;
import io.github.mweirauch.micrometer.jvm.extras.ProcessThreadMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class GatewayConfigure implements InitializingBean {
    SpringDocConfigProperties springDocConfigProperties;
    SwaggerUiConfigParameters swaggerUiConfigParameters;
    RouteDefinitionLocator routeDefinitionLocator;

    public GatewayConfigure(SpringDocConfigProperties springDocConfigProperties,
                            SwaggerUiConfigParameters swaggerUiConfigParameters,
                            RouteDefinitionLocator routeDefinitionLocator) {
        this.springDocConfigProperties = springDocConfigProperties;
        this.swaggerUiConfigParameters = swaggerUiConfigParameters;
        this.routeDefinitionLocator = routeDefinitionLocator;
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer() {
        return (registry) -> {
            new ProcessMemoryMetrics().bindTo(registry);
            new ProcessThreadMetrics().bindTo(registry);
        };
    }

    List<String> ignoreRouteIds = List.of("openapi");

    @Override
    public void afterPropertiesSet() {
        Optional.ofNullable(routeDefinitionLocator.getRouteDefinitions().collectList().block()).orElse(new ArrayList<>())
                .stream()
                .filter(t -> !ignoreRouteIds.contains(t.getId()))
                .forEach(t -> {
                    AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl(
                            t.getId(),
                            springDocConfigProperties.getApiDocs().getPath() + "/" + t.getId(), ""
                    );
                    swaggerUiConfigParameters.getUrls().add(swaggerUrl);
                });
    }
}
