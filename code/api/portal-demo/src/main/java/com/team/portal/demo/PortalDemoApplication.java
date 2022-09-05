package com.team.portal.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {"com.team"})
@SpringBootApplication
public class PortalDemoApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PortalDemoApplication.class);
        app.setAllowCircularReferences(true);
        app.run(args);
    }
}
