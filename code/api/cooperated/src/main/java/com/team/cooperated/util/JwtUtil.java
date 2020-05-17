package com.team.cooperated.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil<T> {

    private T user;

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }

    public static StringRedisTemplate redisTemplate;

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void init() {
        redisTemplate = stringRedisTemplate;
    }


    public static String generateToken(String loginName, String password) {
        String token = JWT.create().withClaim("loginName", loginName).sign(Algorithm.HMAC256(password));
        redisTemplate.opsForValue().set(loginName, token, 60, TimeUnit.MINUTES);
        return token;
    }

    public static Boolean verify(String token, String loginName, String password) {
        try {
            String redisToken = redisTemplate.opsForValue().get(loginName);
            if (redisToken != null && redisToken.equals(token)) {
                JWT.require(Algorithm.HMAC256(password)).withClaim("loginName", loginName).build().verify(token);
                return redisTemplate.expire(loginName, 60, TimeUnit.MINUTES);
            }
        } catch (Exception exception) {
            return false;
        }
        return false;
    }

    public static String getLoginName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
