package com.team.core.universal;

import com.team.entity.BaseEntity;
import com.team.entity.mongo.Log;
import com.team.repository.mongo.MongoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class MongoBaseServiceImpl<TEntity extends BaseEntity> implements MongoBaseService<TEntity> {

    @Autowired
    protected MongoBaseRepository<TEntity> mongoRepository;
}
