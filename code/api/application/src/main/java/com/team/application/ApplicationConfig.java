package com.team.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.Map;

@Configuration
public class ApplicationConfig {


    @Bean
    public RedisTemplate<String, Map<String, Object>> stringListRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Map<String, Object>> stringListRedisTemplate = new RedisTemplate<>();
        stringListRedisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        stringListRedisTemplate.setDefaultSerializer(serializer);
        return stringListRedisTemplate;
    }

}
