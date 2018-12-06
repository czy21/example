package com.team.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.team.entity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.Date;

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

    /**
     * 生成 token
     *
     * @param loginName 用户名
     * @param password  密码
     * @return 加密的token
     */
    public static String GenerateToken(String loginName, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            String token = JWT.create().withClaim("loginName", loginName).sign(algorithm);
            redisUtil.set(loginName, token, RedisUtil.TOKEN_EXPIRES_MINUTE);
            return token;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验 token 是否正确
     *
     * @param token     密钥
     * @param loginName 用户名
     * @param password  密码
     * @return 是否正确
     */
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

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String GetLoginName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
