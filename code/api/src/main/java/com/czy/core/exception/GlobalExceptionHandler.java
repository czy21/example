package com.czy.core.exception;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(WebException.class)
    public Object handleException(WebException e) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", e.getStatus());
        jsonObject.put("message", e.getMessage());
        return jsonObject;
    }
}
