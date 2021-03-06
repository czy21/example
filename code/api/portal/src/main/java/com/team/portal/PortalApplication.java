package com.team.portal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {"com.team"})
@SpringBootApplication
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PortalApplication.class);
        app.setAllowCircularReferences(true);
        app.run(args);
    }
}
