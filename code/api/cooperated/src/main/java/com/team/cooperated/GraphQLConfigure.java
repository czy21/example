package com.team.cooperated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oembedler.moon.graphql.boot.error.ErrorHandlerSupplier;
import com.team.cooperated.graphql.CustomGraphQLErrorHandler;
import com.team.cooperated.graphql.CustomGraphQLObjectMapper;
import com.team.cooperated.graphql.CustomObjectMapperConfigurer;
import graphql.servlet.config.ConfiguringObjectMapperProvider;
import graphql.servlet.core.GraphQLErrorHandler;
import graphql.servlet.core.GraphQLObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfigure {

    @Bean
    public GraphQLErrorHandler graphqlErrorhandler() {
        return new CustomGraphQLErrorHandler();
    }

    @Bean
    public GraphQLObjectMapper graphQLObjectMapper(GraphQLErrorHandler errorHandler, ObjectMapper objectMapper) {
        return new CustomGraphQLObjectMapper(new ConfiguringObjectMapperProvider(objectMapper, new CustomObjectMapperConfigurer()), new ErrorHandlerSupplier(errorHandler));
    }


}
