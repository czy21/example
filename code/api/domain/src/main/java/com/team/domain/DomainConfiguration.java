package com.team.domain;

import com.team.domain.mapper.UserMapper;
import com.team.domain.model.CompanyEntity;
import com.team.domain.repository.jpa.CompanyRepository;
import com.team.domain.repository.mongo.LogRepository;
import com.team.infrastructure.base.MongoBaseRepositoryImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = LogRepository.class, repositoryBaseClass = MongoBaseRepositoryImpl.class)
@MapperScan(basePackageClasses = {UserMapper.class})
@EntityScan(basePackageClasses = CompanyEntity.class)
@EnableJpaRepositories(basePackageClasses = CompanyRepository.class)
@EnableJpaAuditing
public class DomainConfiguration {

}
