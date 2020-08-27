package com.team.domain;

import com.team.domain.mapper.UserMapper;
import com.team.domain.node.CompanyNode;
import com.team.domain.repository.mongo.LogRepository;
import com.team.domain.repository.neo4j.CompanyRepository;
import com.team.infrastructure.base.MongoBaseRepositoryImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@MapperScan(basePackageClasses = {UserMapper.class})
@EntityScan(basePackageClasses = {CompanyNode.class})
@EnableMongoRepositories(basePackageClasses = LogRepository.class, repositoryBaseClass = MongoBaseRepositoryImpl.class)
@EnableNeo4jRepositories(basePackageClasses = CompanyRepository.class)
public class DomainConfiguration {

}
