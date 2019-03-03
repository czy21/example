package com.team.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public Object handle401() {
        return "您没有权限访问！";
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public Object globalException(HttpServletRequest request, Throwable ex) {
        return "访问出错，无法访问: ";
    }

}
