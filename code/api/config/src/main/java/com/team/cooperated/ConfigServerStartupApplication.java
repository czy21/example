package com.team.cooperated;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerStartupApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerStartupApplication.class, args);
    }
}