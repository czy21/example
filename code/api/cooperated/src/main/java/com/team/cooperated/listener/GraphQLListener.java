package com.team.cooperated.listener;

import graphql.servlet.core.GraphQLServletListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GraphQLListener implements GraphQLServletListener {

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        return new GraphQLServletListener.RequestCallback() {
            @Override
            public void onSuccess(HttpServletRequest request, HttpServletResponse response) {
                System.out.println("success");
            }

            @Override
            public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onFinally(HttpServletRequest request, HttpServletResponse response) {
                System.out.println("finally");
            }
        };
    }


}
