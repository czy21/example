package com.team;

import com.team.domain.infrastructure.MongoBaseRepository;
import com.team.domain.infrastructure.MongoBaseRepositoryImpl;
import com.team.domain.infrastructure.MybatisBaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = MongoBaseRepository.class, repositoryBaseClass = MongoBaseRepositoryImpl.class)
@MapperScan(basePackageClasses = MybatisBaseMapper.class)
public class RepositoryConfigure {
}
