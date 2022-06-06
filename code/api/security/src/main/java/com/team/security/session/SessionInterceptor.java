package com.team.security.session;

import com.team.cooperated.exception.BusinessErrorKind;
import com.team.security.annotation.Anonymous;
import com.team.security.annotation.Login;
import com.team.security.exception.SessionErrorKind;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class SessionInterceptor implements HandlerInterceptor {

    public static String COOKIE_NAME = "sid";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            var annotations = Arrays.asList(((HandlerMethod) handler).getMethod().getAnnotations());
            var skipAnnotations = List.of(Login.class, Anonymous.class);
            if (annotations.stream().anyMatch(t -> skipAnnotations.contains(t.annotationType()))) {
                return HandlerInterceptor.super.preHandle(request, response, handler);
            }
        }

        boolean hasCookie = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[]{})).anyMatch(t -> t.getName().equals(COOKIE_NAME));
        if (!hasCookie) {
            throw new BusinessException(SessionErrorKind.COOKIE_VALUE_IS_NULL);
        }
        boolean hasSession = request.getSession(false) == null;
        if (hasSession) {
            throw new BusinessException(BusinessErrorKind.LOGIN_TIMEOUT);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
