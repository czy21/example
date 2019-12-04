package com.team.cooperated.exception;


import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public BusinessException(BusinessErrorCode businessErrorCode, String message) {
        super(message);
        extensions.put("code", businessErrorCode);
        extensions.put("message", message);
    }

    public BusinessException(BusinessErrorCode businessErrorCode) {
        this(businessErrorCode, businessErrorCode.getDefaultMessage());
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

}
