package com.team.portal.configure;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(value = "com.team")
@Configuration
public class PortalConfiguration {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

}
