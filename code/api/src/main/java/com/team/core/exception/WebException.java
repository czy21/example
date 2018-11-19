package com.team.core.exception;


public class WebException extends RuntimeException {

    private String ErrorCode;

    public String getErrorCode() {
        return ErrorCode;
    }

    public WebException(com.team.core.exception.ErrorCode _errorCode, String _message) {
        super(_message);
        ErrorCode = _errorCode.toString();
    }
}
