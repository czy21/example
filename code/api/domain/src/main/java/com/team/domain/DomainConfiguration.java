package com.team.domain;

import com.team.domain.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackageClasses = {UserMapper.class})
public class DomainConfiguration {

}
