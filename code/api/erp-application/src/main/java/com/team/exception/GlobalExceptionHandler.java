package com.team.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
//    @ExceptionHandler(BadCredentialsException.class)
//    public Object handle400(BadCredentialsException e) {
//        return e.getMessage();
//    }
//
//    @ExceptionHandler(Exception.class)
//    public Object handle401() {
//        return "无权限访问";
//    }
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public Object noUserName(){
//        return "没有用户名密码";
//    }
}
