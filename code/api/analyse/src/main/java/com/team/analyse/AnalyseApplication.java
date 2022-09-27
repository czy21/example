package com.team.analyse;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AnalyseApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AnalyseApplication.class);
        app.setAllowCircularReferences(true);
        app.run(args);
    }
}
