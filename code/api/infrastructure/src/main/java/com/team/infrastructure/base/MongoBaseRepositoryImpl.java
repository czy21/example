package com.team.infrastructure.base;

import com.team.infrastructure.base.MongoBaseRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

public class MongoBaseRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements MongoBaseRepository<T, ID> {

    protected final MongoOperations mongoOperations;

    protected final MongoEntityInformation<T, ID> entityInformation;

    private Class<T> clazz;

    public MongoBaseRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoOperations = mongoOperations;
        this.entityInformation = metadata;
        clazz = entityInformation.getJavaType();
    }

    @Override
    public T update(ID id, T entity) {
        Update update = new Update();
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        Field[] fields = clazz.getDeclaredFields();
        for (Field filed : fields) {
            filed.setAccessible(true);
            try {
                Object object = filed.get(entity);
                if (object != null) {
                    update.set(filed.getName(), object);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        this.mongoOperations.updateFirst(query, update, clazz);
        return super.findById(id).orElse(null);
    }

    @Override
    public T update(ID id, Map<String, Object> updateFieldMap) {
        if (updateFieldMap != null && !updateFieldMap.isEmpty()) {
            Criteria criteria = new Criteria("_id").is(id);
            Update update = new Update();
            updateFieldMap.forEach(update::set);
            mongoOperations.updateFirst(new Query(criteria), update, clazz);
        }
        return super.findById(id).orElse(null);
    }
}
