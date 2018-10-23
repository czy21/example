package com.czy.core.exception;

public class WebException extends RuntimeException {

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WebException(String _status, String _message) {
        status = _status;
        message = _message;
    }
}
