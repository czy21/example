package com.team.fileresolve;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FileResolveApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileResolveApplication.class, args);
    }
}
