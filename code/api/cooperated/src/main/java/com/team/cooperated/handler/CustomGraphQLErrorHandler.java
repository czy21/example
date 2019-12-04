package com.team.cooperated.handler;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.servlet.core.DefaultGraphQLErrorHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomGraphQLErrorHandler extends DefaultGraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        return errors.stream().map(this::transform).collect(Collectors.toList());
    }

    public GraphQLError transform(GraphQLError error) {
        GraphQLErrorModel model = new GraphQLErrorModel(null);
        model.setExtensions(error.getExtensions());
        return model;
    }

    private static class GraphQLErrorModel implements GraphQLError {

        private String message;
        private Map<String, Object> extensions;

        public GraphQLErrorModel(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public Map<String, Object> getExtensions() {
            return extensions;
        }

        public void setExtensions(Map<String, Object> extensions) {
            this.extensions = extensions;
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
}
