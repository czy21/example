package com.team.core.configure;

import com.team.repository.mongo.MongoBaseRepository;
import com.team.repository.mongo.MongoBaseRepositoryImpl;
import com.team.repository.mybatis.MybatisBaseRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = MongoBaseRepository.class, repositoryBaseClass = MongoBaseRepositoryImpl.class)
@MapperScan(basePackageClasses = MybatisBaseRepository.class)
public class RepositoryConfigure {
}
