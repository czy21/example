package com.team.orm;


import com.team.orm.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackageClasses = UserMapper.class)
@SpringBootApplication
public class OrmApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrmApplication.class, args);
    }
}
