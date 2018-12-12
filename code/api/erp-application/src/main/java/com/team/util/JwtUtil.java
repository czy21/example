package com.team.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.team.entity.system.User;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class JwtUtil {

    private static RedisUtil redisUtil;

    public JwtUtil(RedisUtil _redisUtil) {
        redisUtil = _redisUtil;
    }

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        JwtUtil.currentUser = currentUser;
    }

    public static String GenerateToken(String loginName, String password) {
        try {
            String token = JWT.create().withClaim("loginName", loginName).sign(Algorithm.HMAC256(password));
            redisUtil.set(loginName, token, RedisUtil.TOKEN_EXPIRES_MINUTE);
            return token;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static boolean Verify(String token, String loginName, String password) {
        try {
            String redisToken = redisUtil.get(loginName);
            if (redisToken != null && redisToken.equals(token)) {
                JWT.require(Algorithm.HMAC256(password)).withClaim("loginName", loginName).build().verify(token);
                return redisUtil.expire(loginName, RedisUtil.TOKEN_EXPIRES_MINUTE);
            }
        } catch (Exception exception) {
            return false;
        }
        return false;
    }

    public static String GetLoginName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
