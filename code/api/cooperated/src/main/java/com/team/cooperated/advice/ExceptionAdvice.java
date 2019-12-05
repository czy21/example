package com.team.cooperated.advice;

import com.team.cooperated.controller.BaseController;
import com.team.cooperated.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    public static final String UN_KNOW_SERVER_ERROR = "UN_KNOW_SERVER_ERROR";

    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> exceptionHandler(Exception e) {
        Map<String, Object> result = new HashMap<>();
        result.put(BaseController.RESPONSE_TIMESTAMP_KEY, LocalDateTime.now());
        if (e instanceof BusinessException) {
            result.put(BaseController.RESPONSE_ERROR_KEY, ((BusinessException) e).getExtensions());
        } else {
            Map<String, Object> errorModel = new HashMap<>();
            errorModel.put("code", UN_KNOW_SERVER_ERROR);
            errorModel.put("message", e.getMessage());
            result.put(BaseController.RESPONSE_ERROR_KEY, errorModel);
        }
        e.printStackTrace();
        return result;
    }
}
