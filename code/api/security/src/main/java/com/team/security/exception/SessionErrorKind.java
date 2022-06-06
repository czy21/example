package com.team.security.exception;


import com.czy.learning.infranstructure.exception.ExceptionMessage;

public enum SessionErrorKind implements ExceptionMessage {
    COOKIE_VALUE_IS_NULL("cookie_value_is_null", "请登录");

    String code;
    String message;

    SessionErrorKind(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
