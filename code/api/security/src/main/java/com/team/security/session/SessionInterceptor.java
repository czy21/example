package com.team.security.session;

import com.team.cooperated.exception.BusinessException;
import com.team.security.exception.SessionErrorKind;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;


public class SessionInterceptor implements HandlerInterceptor {

    public static String COOKIE_NAME = "sid";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean hasCookie = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[]{})).anyMatch(t -> t.getName().equals(COOKIE_NAME));
        if (!hasCookie) {
            throw new BusinessException(SessionErrorKind.COOKIE_VALUE_IS_NULL);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
