package com.team.fileresolve.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisMessageConsumer extends Thread {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void run() {
        while (true) {
            var msg = stringRedisTemplate.opsForList().rightPop("kf:log:token2", 1000L, TimeUnit.SECONDS);
            System.out.println(msg);
        }
    }
}
