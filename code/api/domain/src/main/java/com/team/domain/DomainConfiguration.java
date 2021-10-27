package com.team.domain;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.team.domain.mapper.UserMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.infrastructure.base.MongoBaseRepositoryImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackageClasses = {UserMapper.class})
@EnableMongoRepositories(basePackageClasses = {FileColumnMappingRepository.class},repositoryBaseClass = MongoBaseRepositoryImpl.class)
public class DomainConfiguration {

    @Primary
    @Bean(name = "primaryDS")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public HikariDataSource primaryDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "secondDS")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public HikariDataSource secondDS() {
        return new HikariDataSource();
    }


    @Bean
    @Primary
    JdbcTemplate jdbcTemplate(@Qualifier("primaryDS") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondJdbcTemplate")
    @Qualifier("secondJdbcTemplate")
    public JdbcTemplate mdmJdbcTemplate(@Qualifier("secondDS") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
