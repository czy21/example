package com.team.application.exception;


public class BusinessException extends RuntimeException {

    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public BusinessException(BusinessErrorCode businessErrorCode, String _message) {
        super(_message);
        this.errorCode = businessErrorCode.toString();
    }
}
