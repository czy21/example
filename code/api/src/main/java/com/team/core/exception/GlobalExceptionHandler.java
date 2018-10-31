package com.team.core.exception;

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
        jsonObject.put("ErrorCode", e.getErrorCode());
        jsonObject.put("Message", e.getMessage());
        return jsonObject;
    }
}
