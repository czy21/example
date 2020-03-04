package com.team.cooperated.exception;


import java.util.HashMap;
import java.util.Map;

public class BusinessException extends RuntimeException {

    private Map<String, Object> extensions = new HashMap<>();

    public BusinessException(BusinessErrorCode businessErrorCode, String message) {
        super(message);
        extensions.put("code", businessErrorCode);
        extensions.put("message", message);
    }

    public BusinessException(BusinessErrorCode businessErrorCode) {
        this(businessErrorCode, businessErrorCode.getDefaultMessage());
    }

    public Map<String, Object> getExtensions() {
        return extensions;
    }

}
