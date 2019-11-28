package com.team.application.exception;

import lombok.Data;

@Data
public class ErrorModel {
    private String code;
    private String message;

    public ErrorModel(Object code, String message) {
        this.code = code.toString();
        this.message = message;
    }

    public static String toJSONString(Object code, String message) {
//        return JSONObject.toJSONString(new ErrorModel(code.toString(), message));
        return "";
    }


}
