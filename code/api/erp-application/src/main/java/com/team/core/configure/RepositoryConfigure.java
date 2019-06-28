package com.team.core.configure;

import com.team.repository.mongo.MongoBaseRepositoryImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.team.repository.mongo", repositoryBaseClass = MongoBaseRepositoryImpl.class)
@MapperScan({"com.team.repository.mybatis"})
public class RepositoryConfigure {
}
