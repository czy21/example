package com.team.fileresolve.configure;

import com.team.fileresolve.receiver.RedisLogReceiver;
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
import org.springframework.util.ErrorHandler;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@ComponentScan(value = {"com.team"})
@Configuration
public class FileResolveConfiguration {

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
                .batchSize(100)
                .pollTimeout(Duration.ZERO)
                .build();
        var listenerContainer = StreamMessageListenerContainer
                .create(redisConnectionFactory, options);
        StreamMessageListenerContainer.ConsumerStreamReadRequest<String> readOptions = StreamMessageListenerContainer.StreamReadRequest
                .builder(StreamOffset.create(RedisLogReceiver.KEY, ReadOffset.lastConsumed()))
                .cancelOnError((ex) -> false)
                .consumer(Consumer.from(RedisLogReceiver.GROUP, "consumer-1"))
                .errorHandler(t -> {
                })
                .build();
        var subscription = listenerContainer.register(readOptions, streamListener);
        listenerContainer.start();
        return subscription;
    }
}
