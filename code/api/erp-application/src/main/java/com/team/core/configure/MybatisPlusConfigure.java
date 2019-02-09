package com.team.core.configure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.team.repository.mybatis","com.baidu.fsg.uid.worker.dao"})
public class MybatisPlusConfigure {

}
