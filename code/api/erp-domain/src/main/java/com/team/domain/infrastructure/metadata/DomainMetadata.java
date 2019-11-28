package com.team.domain.infrastructure.metadata;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class DomainMetadata implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("insertFill");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("updateFill");
    }
}
