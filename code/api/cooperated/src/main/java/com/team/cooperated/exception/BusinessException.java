package com.team.cooperated.exception;


import org.apache.commons.lang3.StringUtils;

public class BusinessException extends RuntimeException {

    private String code;

    public String getCode() {
        return code;
    }

    public BusinessException(BusinessErrorCode businessErrorCode, String message) {
        super(StringUtils.isEmpty(message) ? businessErrorCode.getDefaultMessage() : message);
        this.code = businessErrorCode.toString();
    }

    public BusinessException(BusinessErrorCode businessErrorCode) {
        this(businessErrorCode, null);
    }

}
