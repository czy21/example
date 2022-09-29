package com.team.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(KafkaApplication.class);
        app.setAllowCircularReferences(true);
        app.run(args);
    }
}
