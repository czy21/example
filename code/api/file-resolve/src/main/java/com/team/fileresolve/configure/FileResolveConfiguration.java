package com.team.fileresolve.configure;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

@ComponentScan(value = "com.team")
@Configuration
public class FileResolveConfiguration implements InitializingBean {

    StringRedisTemplate redisTemplate;

    public FileResolveConfiguration(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public Subscription subscription(RedisConnectionFactory redisConnectionFactory, StreamListener<String, ObjectRecord<String, Object>> streamListener) throws UnknownHostException {
        var options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofMillis(100))
                .targetType(Object.class)
                .build();
        var listenerContainer = StreamMessageListenerContainer
                .create(redisConnectionFactory, options);
        var subscription = listenerContainer.receiveAutoAck(
                Consumer.from("kf-log-token-group", "consumer-1"),
                StreamOffset.create("kf:log:token", ReadOffset.lastConsumed()),
                streamListener);
        listenerContainer.start();
        return subscription;
    }

    // XGROUP CREATE kf:log:token kf-log-token-group 0-0 MKSTREAM
    @Override
    public void afterPropertiesSet() {
        try {
            redisTemplate.opsForStream().createGroup("kf:log:token", ReadOffset.from("0-0"), "kf-log-token-group");
        } catch (Exception e) {
            if (!e.getMessage().startsWith("BUSYGROUP Consumer Group name already exists")) {
                throw e;
            }
        }
    }
}
