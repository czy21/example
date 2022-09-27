package com.team.stream.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Configuration
public class Example1 {

    @Bean
    public Consumer<List<String>> input1() {
        return list -> {
            list.forEach(System.out::println);
        };
    }
}
