package com.team.application;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.Map;

@Configuration
public class ApplicationConfig {

    public static final String FILE_RESOLVE_QUEUE = "fileResolve";

    @Bean
    public RedisTemplate<String, Map<String, Object>> stringListRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Map<String, Object>> stringListRedisTemplate = new RedisTemplate<>();
        stringListRedisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        stringListRedisTemplate.setDefaultSerializer(serializer);
        return stringListRedisTemplate;
    }
    @Bean
    public Queue fileResolve() {
        return new Queue(FILE_RESOLVE_QUEUE);
    }
}
