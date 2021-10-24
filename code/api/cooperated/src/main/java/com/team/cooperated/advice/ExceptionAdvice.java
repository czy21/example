package com.team.cooperated.advice;

import com.team.cooperated.controller.BaseController;
import com.team.cooperated.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvice {

    public static final String UN_KNOW_SERVER_ERROR = "UN_KNOW_SERVER_ERROR";

    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> exceptionHandler(Exception e) {
        Map<String, Object> result = new HashMap<>();
        result.put(BaseController.RESPONSE_TIMESTAMP_KEY, LocalDateTime.now());
        Map<String, Object> error = new HashMap<>();
        error.put("code", e instanceof BusinessException ? ((BusinessException) e).getCode() : UN_KNOW_SERVER_ERROR);
        error.put("message", e.getMessage());
        result.put(BaseController.RESPONSE_ERROR_KEY, error);
        e.printStackTrace();
        return result;
    }

}
