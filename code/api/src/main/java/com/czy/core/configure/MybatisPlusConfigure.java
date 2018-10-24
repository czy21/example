package com.czy.core.configure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
@MapperScan(value = "com.czy.dao*")
public class MybatisPlusConfigure {
}
