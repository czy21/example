package com.czy.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
@MapperScan(value = "com.czy.dao*")
public class MybatisPlusConfig {
}
