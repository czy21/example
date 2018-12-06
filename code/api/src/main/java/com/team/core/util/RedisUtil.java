package com.team.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    public static final long TOKEN_EXPIRES_MINUTE = 30;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 向redis中设值
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 向redis中设置，同时设置过期时间
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, String value, long time) {
        redisTemplate.opsForValue().set(key, value);
        return expire(key, time);
    }

    /**
     * 设置key的过期时间
     *
     * @param key
     * @param time
     * @return
     */
    public Boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.MINUTES);
    }

    /**
     * 获取redis中的值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据key删除对应value
     *
     * @param key
     * @return
     */
    public Boolean remove(String key) {
        return redisTemplate.delete(key);
    }
}
