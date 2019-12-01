package com.team.cooperated.advice;

import com.team.cooperated.controller.BaseController;
import com.team.cooperated.exception.BusinessException;
import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    private static final String UN_KNOW_SERVER_ERROR = "UN_KNOW_SERVER_ERROR";

    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> exceptionHandler(Exception e) {
        return Map.of(BaseController.RESPONSE_TIMESTAMP_KEY, LocalDateTime.now(), BaseController.RESPONSE_ERROR_KEY, ErrorModel.of((e instanceof BusinessException) ? ((BusinessException) e).getCode() : UN_KNOW_SERVER_ERROR, e.getMessage()));
    }

    @Data
    private static class ErrorModel {
        private String code;
        private String message;

        public ErrorModel(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static ErrorModel of(String code, String message) {
            return new ErrorModel(code, message);
        }

    }
}
