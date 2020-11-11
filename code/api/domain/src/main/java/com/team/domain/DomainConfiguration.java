package com.team.domain;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.team.domain.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackageClasses = {UserMapper.class})
public class DomainConfiguration {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(-1);
        return paginationInterceptor;
    }
}
