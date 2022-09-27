package com.team.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@SpringBootApplication
public class StreamApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StreamApplication.class);
        app.setAllowCircularReferences(true);
        app.run(args);
    }

    @Bean
    public Consumer<List<Map<String, Object>>> input() {
        return list -> {
            System.out.println("Received " + list.size());
            list.forEach(thing -> {
                System.out.println(thing);
            });
        };
    }

//    @Bean
//    public ApplicationRunner runner(RabbitTemplate template) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return args -> {
//            template.convertAndSend("input-in-0.someGroup", objectMapper.writeValueAsString(Map.of("name", "1")));
//            template.convertAndSend("input-in-0.someGroup", objectMapper.writeValueAsString(Map.of("name", "1")));
//        };
//    }
}
