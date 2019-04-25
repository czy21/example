package com.team.core.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationHandler implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String message = "";
        if (authException instanceof InsufficientAuthenticationException) {
            message = "Token验证失败";
        }
        if (StringUtils.isEmpty(message)) {
            message = authException.getMessage();
        }
        response.sendError(HttpStatus.UNAUTHORIZED.value(), message);
    }
}
