package com.team.infrastructure.metadata;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;


public class EntityMetadataHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createdDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("modifiedDate", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifiedDate", LocalDateTime.now(), metaObject);
    }
}
