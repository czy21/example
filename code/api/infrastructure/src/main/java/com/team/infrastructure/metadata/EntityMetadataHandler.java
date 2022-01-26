package com.team.infrastructure.metadata;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;


public class EntityMetadataHandler implements MetaObjectHandler {

    public final static String CREATE_TIME_FIELD = "createTime";
    public final static String UPDATE_TIME_FIELD = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime nowTime = LocalDateTime.now();
        Object createDate = this.getFieldValByName(CREATE_TIME_FIELD, metaObject);
        if (createDate == null) {
            this.setFieldValByName(CREATE_TIME_FIELD, nowTime, metaObject);
        }
        Object modifiedDate = this.getFieldValByName(UPDATE_TIME_FIELD, metaObject);
        if (modifiedDate == null) {
            this.setFieldValByName(UPDATE_TIME_FIELD, nowTime, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime nowTime = LocalDateTime.now();
        Object modifiedDate = this.getFieldValByName(UPDATE_TIME_FIELD, metaObject);
        if (modifiedDate == null) {
            this.setFieldValByName(UPDATE_TIME_FIELD, nowTime, metaObject);
        }
    }
}
