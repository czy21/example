package com.team.config;

import com.team.impl.DataResponseImpl;
import com.team.provider.DataResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResponseConfig {

    @Bean
    public DataResponse resourceResponse() {
        return new DataResponseImpl("test");
    }


    @Bean
    public DataResponse resourceResponsea() {
        return new DataResponseImpl("test");
    }


}
