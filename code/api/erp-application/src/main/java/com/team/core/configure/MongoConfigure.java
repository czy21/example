package com.team.core.configure;

import com.team.repository.mongo.MongoBaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.team.repository.mongo", repositoryBaseClass = MongoBaseRepositoryImpl.class)
public class MongoConfigure {
}
