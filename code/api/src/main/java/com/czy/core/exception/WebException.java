package com.czy.core.exception;

public class WebException extends RuntimeException {

    private String ErrorCode;

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public WebException(String _errorCode, String _message) {
        super(_message);
        ErrorCode = _errorCode;
    }
}
