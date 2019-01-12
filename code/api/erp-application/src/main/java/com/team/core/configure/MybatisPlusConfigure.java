package com.team.core.configure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.team.repository.mybatis")
public class MybatisPlusConfigure {

}
