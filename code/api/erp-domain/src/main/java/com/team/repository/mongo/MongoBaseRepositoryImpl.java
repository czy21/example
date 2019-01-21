package com.team.repository.mongo;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

public class MongoBaseRepositoryImpl<TEntity, ID extends Serializable> extends SimpleMongoRepository<TEntity, ID> implements MongoBaseRepository<TEntity, ID> {

    protected final MongoOperations mongoTemplate;

    protected final MongoEntityInformation<TEntity, ID> entityInformation;

    private Class<TEntity> clazz;

    public MongoBaseRepositoryImpl(MongoEntityInformation<TEntity, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoTemplate = mongoOperations;
        this.entityInformation = metadata;
        clazz = entityInformation.getJavaType();
    }

    @Override
    public TEntity update(ID id, TEntity t) {
        Update update = new Update();
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        Field[] fields = clazz.getDeclaredFields();
        for (Field filed : fields) {
            filed.setAccessible(true);
            try {
                Object object = filed.get(t);
                if (object != null) {
                    update.set(filed.getName(), object);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        this.mongoTemplate.updateFirst(query, update, clazz);
        return super.findById(id).orElse(null);
    }

    @Override
    public TEntity update(ID id, Map<String, Object> updateFieldMap) {
        if (updateFieldMap != null && !updateFieldMap.isEmpty()) {
            Criteria criteria = new Criteria("_id").is(id);
            Update update = new Update();
            updateFieldMap.forEach(update::set);
            mongoTemplate.updateFirst(new Query(criteria), update, clazz);
        }
        return super.findById(id).orElse(null);
    }
}
