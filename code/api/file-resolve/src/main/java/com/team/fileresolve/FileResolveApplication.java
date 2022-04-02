package com.team.fileresolve;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FileResolveApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FileResolveApplication.class);
        app.setAllowCircularReferences(true);
        app.run(args);
    }
}
