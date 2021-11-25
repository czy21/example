package com.team.domain;

import com.team.domain.mapper.UserMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.infrastructure.entity.MongoBaseRepositoryImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@MapperScan(basePackageClasses = {UserMapper.class})
@EnableMongoRepositories(basePackageClasses = {FileColumnMappingRepository.class}, repositoryBaseClass = MongoBaseRepositoryImpl.class)
public class DomainConfiguration {

}
