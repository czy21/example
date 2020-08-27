package com.team.domain;

import com.team.domain.mapper.UserMapper;
import com.team.domain.model.CompanyEntity;
import com.team.domain.node.CompanyNode;
import com.team.domain.repository.jpa.CompanyJpaRepository;
import com.team.domain.repository.mongo.LogMongoRepository;
import com.team.domain.repository.neo4j.CompanyNeo4jRepository;
import com.team.infrastructure.base.MongoBaseRepositoryImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableJpaAuditing
@MapperScan(basePackageClasses = {UserMapper.class})
@EntityScan(basePackageClasses = {CompanyEntity.class, CompanyNode.class})
@EnableMongoRepositories(basePackageClasses = LogMongoRepository.class, repositoryBaseClass = MongoBaseRepositoryImpl.class)
@EnableJpaRepositories(basePackageClasses = {CompanyJpaRepository.class})
@EnableNeo4jRepositories(basePackageClasses = CompanyNeo4jRepository.class)
public class DomainConfiguration {

}
