package com.team.exception;


public class BusinessException extends RuntimeException {

    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public BusinessException(com.team.exception.ErrorCode errorCode, String _message) {
        super(_message);
        this.errorCode = errorCode.toString();
    }
}
