package com.team.cooperated.graphql;

import com.team.cooperated.controller.BaseController;
import graphql.ExecutionResult;
import graphql.servlet.config.ObjectMapperProvider;
import graphql.servlet.core.GraphQLErrorHandler;
import graphql.servlet.core.GraphQLObjectMapper;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;

public class CustomGraphQLObjectMapper extends GraphQLObjectMapper {
    public CustomGraphQLObjectMapper(ObjectMapperProvider objectMapperProvider, Supplier<GraphQLErrorHandler> errorHandlerSupplier) {
        super(objectMapperProvider, errorHandlerSupplier);
    }

    @Override
    public Map<String, Object> convertSanitizedExecutionResult(ExecutionResult executionResult, boolean includeData) {
        Map<String, Object> map = super.convertSanitizedExecutionResult(executionResult, includeData);
        map.put(BaseController.RESPONSE_TIMESTAMP_KEY, new Date());
        return map;
    }
}
