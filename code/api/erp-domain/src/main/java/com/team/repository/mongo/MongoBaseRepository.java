package com.team.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MongoBaseRepository<TEntity> extends MongoRepository<TEntity, String> {
}
