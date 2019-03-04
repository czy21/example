package com.team.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class ErrorModel {
    private String errorCode;
    private String message;

    public ErrorModel(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static String toJSONString(ErrorCode code, String message) {
        return JSONObject.toJSONString(new ErrorModel(code.toString(), message));
    }


}
