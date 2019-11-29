package com.team.application.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean set(String key, String value, long time) {
        redisTemplate.opsForValue().set(key, value);
        return expire(key, time);
    }

    public Boolean expire(String key, long time) {
        return time == 0 ? true : redisTemplate.expire(key, time, TimeUnit.MINUTES);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean remove(String key) {
        return redisTemplate.delete(key);
    }
}
