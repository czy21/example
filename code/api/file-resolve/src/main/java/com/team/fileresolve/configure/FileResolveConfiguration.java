package com.team.fileresolve.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

@ComponentScan(value = "com.team")
@Configuration
public class FileResolveConfiguration {

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

}
