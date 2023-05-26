package com.team.domain;

import com.team.domain.mapper.UserMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.infrastructure.entity.MongoBaseRepositoryImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@MapperScan(basePackageClasses = {UserMapper.class})
@EnableMongoRepositories(basePackageClasses = {FileColumnMappingRepository.class}, repositoryBaseClass = MongoBaseRepositoryImpl.class)
public class DomainConfiguration implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
//        Map<String,String> transMap=new HashMap<>();
//        transMap.put("0","生效");
//        transMap.put("1","失效");
//        dictionaryTransService.makeUseRedis();
//        dictionaryTransService.refreshCache("status",transMap);
    }
}
