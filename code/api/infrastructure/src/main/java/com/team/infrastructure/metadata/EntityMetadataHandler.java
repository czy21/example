package com.team.infrastructure.metadata;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;


public class EntityMetadataHandler implements MetaObjectHandler {

    public final static String CREATE_DATE_NAME = "createdDate";
    public final static String MODIFIED_DATE_NAME = "modifiedDate";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime nowTime = LocalDateTime.now();
        Object createDate = this.getFieldValByName(CREATE_DATE_NAME, metaObject);
        if (createDate == null) {
            this.setFieldValByName(CREATE_DATE_NAME, nowTime, metaObject);
        }
        Object modifiedDate = this.getFieldValByName(MODIFIED_DATE_NAME, metaObject);
        if (modifiedDate == null) {
            this.setFieldValByName(MODIFIED_DATE_NAME, nowTime, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime nowTime = LocalDateTime.now();
        Object modifiedDate = this.getFieldValByName(MODIFIED_DATE_NAME, metaObject);
        if (modifiedDate == null) {
            this.setFieldValByName(MODIFIED_DATE_NAME, nowTime, metaObject);
        }
    }
}
