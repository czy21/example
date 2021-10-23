package com.team.cooperated.exception;

import kind.ExceptionMessage;

public enum BusinessErrorCode implements ExceptionMessage {
    NO_EXIST_USER("no_exist_user", "用户不存在"),
    EXIST_USER("exist_user", "用户已存在"),
    EXIST_NAME("exist_name", "名称已存在"),
    NO_NULL_ID("no_null_id", "Id不能为空"),
    NO_NULL_NAME("no_null_name", "名称不能为空"),
    NO_NULL_VALUE("no_null_value", "值不能为空");

    String code;
    String message;

    BusinessErrorCode(String code, String message) {
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
