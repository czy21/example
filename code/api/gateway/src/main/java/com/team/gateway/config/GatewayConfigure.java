package com.team.gateway.config;

import io.github.mweirauch.micrometer.jvm.extras.ProcessMemoryMetrics;
import io.github.mweirauch.micrometer.jvm.extras.ProcessThreadMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.webflux.ui.SwaggerConfigResource;
import org.springdoc.webflux.ui.SwaggerWelcomeCommon;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfigure {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer() {
        return (registry) -> {
            new ProcessMemoryMetrics().bindTo(registry);
            new ProcessThreadMetrics().bindTo(registry);
        };
    }

    @Bean
    SwaggerConfigResource swaggerConfigResource(SwaggerWelcomeCommon swaggerWelcomeCommon,
                                                SwaggerUiConfigParameters swaggerUiConfigParameters,
                                                GatewayProperties gatewayProperties) {
        return new CustomSwaggerConfigResource(swaggerWelcomeCommon, swaggerUiConfigParameters,gatewayProperties);
    }
}
