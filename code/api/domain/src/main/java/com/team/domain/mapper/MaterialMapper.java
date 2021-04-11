package com.team.domain.mapper;

import com.team.domain.entity.MaterialEntity;
import com.team.domain.entity.MaterialTargetEntity;
import com.team.infrastructure.base.MybatisBaseMapper;

public interface MaterialMapper extends MybatisBaseMapper<MaterialEntity> {

    int insertMaterial(MaterialEntity entity);

    MaterialTargetEntity selectMaterialTargetByKey(String key);
}
