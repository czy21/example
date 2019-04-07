package com.team.exception;

public enum SystemErrorCode {

    SUCCESS(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    LOGIN_ERROR(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    OPERATE_ERROR(405),
    TIME_OUT(408),
    SERVER_ERROR(500);

    private int code;

    SystemErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
