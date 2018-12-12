package com.team.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(WebException.class)
    public ErrorModel handleException(WebException e) {
        return new ErrorModel(e.getErrorCode(),e.getMessage());
    }
}
