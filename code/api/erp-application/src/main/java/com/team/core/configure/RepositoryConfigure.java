package com.team.core.configure;

import com.team.infrastructure.MongoBaseRepositoryImpl;
import com.team.infrastructure.MybatisBaseMapper;
import com.team.repository.LogRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = LogRepository.class, repositoryBaseClass = MongoBaseRepositoryImpl.class)
@MapperScan(markerInterface = MybatisBaseMapper.class)
public class RepositoryConfigure {
}
