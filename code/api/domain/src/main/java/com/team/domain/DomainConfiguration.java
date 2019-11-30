package com.team.domain;

import com.team.domain.mapper.UserMapper;
import com.team.domain.repository.LogRepository;
import com.team.infrastructure.base.MongoBaseRepositoryImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = LogRepository.class, repositoryBaseClass = MongoBaseRepositoryImpl.class)
@MapperScan(basePackageClasses = {UserMapper.class})
public class DomainConfiguration {

}
