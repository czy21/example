package com.team.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Map;

@NoRepositoryBean
public interface MongoBaseRepository<TEntity, ID> extends MongoRepository<TEntity, ID> {

    TEntity update(ID id, TEntity t);

    TEntity update(ID id, Map<String, Object> updateFieldMap);


}
