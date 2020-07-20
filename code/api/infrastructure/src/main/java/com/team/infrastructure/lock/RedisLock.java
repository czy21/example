package com.team.infrastructure.lock;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class RedisLock {
    private String key;
    private boolean lock = false;

    private final StringRedisTemplate redisClient;

    /**
     * @param purpose 锁前缀
     * @param key     锁定的ID等东西
     */
    public RedisLock(String purpose, String key, StringRedisTemplate redisClient) {
        this.key = purpose + "_" + key + "_redis_lock";
        this.redisClient = redisClient;
    }

    public boolean lock(long timeout, long expire, final TimeUnit unit) {
        long beginTime = System.currentTimeMillis();
        timeout = unit.toNanos(timeout);
        try {
            // 在timeout的时间范围内不断轮询锁
            while (System.currentTimeMillis() - beginTime < timeout) {
                // 锁不存在的话，设置锁并设置锁过期时间，即加锁
                Boolean setLock = this.redisClient.opsForValue().setIfAbsent(this.key, "1");
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
        try {
            if (this.redisClient.opsForValue().get(this.key) != null) {
                throw new RuntimeException(StringUtils.join(this.key, "正在执行 请稍后"));
            }
            Boolean setLock = this.redisClient.opsForValue().setIfAbsent(this.key, "1");
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
}
