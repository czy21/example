package com.team.mybatis;


import com.team.mybatis.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackageClasses = UserMapper.class)
@SpringBootApplication
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MybatisApplication.class);
        app.setAllowCircularReferences(true);
        app.run(args);
    }
}
