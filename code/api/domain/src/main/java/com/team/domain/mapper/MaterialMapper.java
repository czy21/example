package com.team.domain.mapper;

import com.team.domain.entity.MaterialEntity;
import com.team.domain.entity.MaterialTargetEntity;
import com.team.infrastructure.base.MybatisBaseMapper;
import org.apache.ibatis.annotations.Param;

public interface MaterialMapper {

    MaterialEntity selectById(@Param("id") String id);

    int insertMaterial(MaterialEntity entity);

    MaterialTargetEntity selectMaterialTargetByKey(String key);
}
