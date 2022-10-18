package com.team.webdav;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {"com.team"})
@SpringBootApplication
public class WebDavApplication {
    public static void main(String[] args) {
        new SpringApplication(WebDavApplication.class).run(args);
    }
}
