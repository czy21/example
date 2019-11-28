package com.team.core.configure;

import com.team.domain.infrastructure.MongoBaseRepositoryImpl;
import com.team.domain.mapper.UserMapper;
import com.team.domain.repository.LogRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = LogRepository.class, repositoryBaseClass = MongoBaseRepositoryImpl.class)
@MapperScan(basePackageClasses = {UserMapper.class})
public class RepositoryConfigure {
}
