package com.team.core.exception;


public class WebException extends RuntimeException {

    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public WebException(com.team.core.exception.ErrorCode errorCode, String _message) {
        super(_message);
        this.errorCode = errorCode.toString();
    }
}
