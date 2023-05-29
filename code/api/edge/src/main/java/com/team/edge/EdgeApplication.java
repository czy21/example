package com.team.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.team")
public class EdgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EdgeApplication.class, args);
    }
}
