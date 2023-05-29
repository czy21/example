package com.team.edge.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EdgeQueueConfig {

    public static final String GH_DEVICE_ALERT_EXCHANGE = "gh.device.alert";

    @Bean
    public FanoutExchange deviceAlertFanoutExchange() {
        return new FanoutExchange(GH_DEVICE_ALERT_EXCHANGE);
    }
}
