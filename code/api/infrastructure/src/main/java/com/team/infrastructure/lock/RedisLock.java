package com.team.infrastructure.lock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class RedisLock {
    private boolean lock = false;

    private final RedisTemplate<String, Object> redisClient;

    private DataResolver dataResolver;
    private Object value;
    private String key;
    private ObjectMapper objectMapper;

    /**
     * @param prefix 锁前缀
     * @param value  锁定的ID等东西
     */
    public RedisLock(String prefix,
                     Object value,
                     RedisTemplate<String, Object> redisClient,
                     DataResolver dataResolver,
                     ObjectMapper objectMapper) {
        this.value = value;
        this.dataResolver = dataResolver;
        this.redisClient = redisClient;
        this.objectMapper = objectMapper;
        this.key = parseValue(prefix, value);
    }

    public boolean lock(long timeout, long expire, final TimeUnit unit) {
        long beginTime = System.currentTimeMillis();
        timeout = unit.toNanos(timeout);
        try {
            // 在timeout的时间范围内不断轮询锁
            while (System.currentTimeMillis() - beginTime < timeout) {
                // 锁不存在的话，设置锁并设置锁过期时间，即加锁
                Boolean setLock = this.redisClient.opsForValue().setIfAbsent(key, "1");
                if (setLock != null && setLock) {
                    this.redisClient.expire(key, expire, unit);
                    this.lock = true;
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("locking error", e);
        }
        return false;
    }

    public boolean lock() {
        if (Optional.ofNullable(this.redisClient.hasKey(key)).orElse(false)) {
            throw new RuntimeException(StringUtils.join(key, " processing"));
        }
        try {
            Boolean setLock = this.redisClient.boundValueOps(key).setIfAbsent(objectMapper.writeValueAsString(value));
            this.lock = Optional.ofNullable(setLock).orElse(false);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("locking error", e);
        }
    }


    /**
     * 释放锁
     */
    public void unlock() {
        if (this.lock) {
            redisClient.delete(key);
        }
    }


    public String parseValue(String prefix, Object value) {
        return StringUtils.join(prefix, dataResolver.encodeToIdentify(value));
    }

}
