package com.team.core.universal;

import com.team.entity.BaseEntity;
import com.team.entity.page.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public class MongoBaseServiceImpl<TEntity extends BaseEntity> implements MongoBaseService<TEntity> {

    @Autowired
    private MongoRepository mongoRepository;
}
