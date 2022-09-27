package com.team.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StreamApplication.class);
        app.setAllowCircularReferences(true);
        app.run(args);
    }
}
