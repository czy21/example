package com.team.domain;

import com.fhs.trans.service.impl.DictionaryTransService;
import com.team.domain.mapper.UserMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.infrastructure.entity.MongoBaseRepositoryImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackageClasses = {UserMapper.class})
@EnableMongoRepositories(basePackageClasses = {FileColumnMappingRepository.class}, repositoryBaseClass = MongoBaseRepositoryImpl.class)
public class DomainConfiguration implements InitializingBean {

    @Autowired
    DictionaryTransService dictionaryTransService;

    @Override
    public void afterPropertiesSet() throws Exception {
//        Map<String,String> transMap=new HashMap<>();
//        transMap.put("0","生效");
//        transMap.put("1","失效");
//        dictionaryTransService.makeUseRedis();
//        dictionaryTransService.refreshCache("status",transMap);
    }
}
