package com.team.infrastructure.entity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Map;

@NoRepositoryBean
public interface MongoBaseRepository<T, ID> extends MongoRepository<T, ID> {

    T update(ID id, T entity);

    T update(ID id, Map<String, Object> updateFieldMap);


}
