package com.team.fileresolve.configure;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@ComponentScan(value = {"com.team","com.czy"})
@Configuration
public class FileResolveConfiguration implements InitializingBean {

    StringRedisTemplate redisTemplate;

    public FileResolveConfiguration(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public Subscription subscription(RedisConnectionFactory redisConnectionFactory,
                                     @Qualifier("redisLogReceiver") StreamListener<String, MapRecord<String, String, String>> streamListener) {
        var options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofMillis(100))
                .build();
        var listenerContainer = StreamMessageListenerContainer
                .create(redisConnectionFactory, options);
        var subscription = listenerContainer.receive(
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
