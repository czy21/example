package com.team.exception;

import lombok.Data;

@Data
public class ErrorModel {
    private String errorCode;
    private String message;

    public ErrorModel(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
