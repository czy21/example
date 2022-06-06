package com.team.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class PoolConfig {

    @Bean
    public ExecutorService migratePersistPool() {
        return Executors.newFixedThreadPool(2);
    }
}
