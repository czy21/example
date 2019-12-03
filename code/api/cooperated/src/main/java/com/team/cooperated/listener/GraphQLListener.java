package com.team.cooperated.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.servlet.core.GraphQLServletListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GraphQLListener implements GraphQLServletListener {

    private ObjectMapper objectMapper;

    @Autowired
    public GraphQLListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        return new GraphQLServletListener.RequestCallback() {
            @Override
            public void onSuccess(HttpServletRequest request, HttpServletResponse response) {
//                objectMapper.readValue(response.getWriter().toString(), Map.class)

//                System.out.println("success");
            }

            @Override
            public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onFinally(HttpServletRequest request, HttpServletResponse response) {
                System.out.println("finaly");
            }
        };
    }


}
