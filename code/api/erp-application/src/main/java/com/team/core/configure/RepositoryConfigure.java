package com.team.core.configure;

import com.team.infrastructure.MongoBaseRepository;
import com.team.infrastructure.MongoBaseRepositoryImpl;
import com.team.infrastructure.MybatisBaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = MongoBaseRepository.class, repositoryBaseClass = MongoBaseRepositoryImpl.class)
@MapperScan(basePackageClasses = MybatisBaseMapper.class)
public class RepositoryConfigure {
}
