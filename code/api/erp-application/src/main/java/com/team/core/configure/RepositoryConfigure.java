package com.team.core.configure;

import com.team.repository.mongobase.MongoBaseRepository;
import com.team.repository.mongobase.MongoBaseRepositoryImpl;
import com.team.mapper.base.MybatisBaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = MongoBaseRepository.class, repositoryBaseClass = MongoBaseRepositoryImpl.class)
@MapperScan(basePackageClasses = MybatisBaseMapper.class)
public class RepositoryConfigure {
}
