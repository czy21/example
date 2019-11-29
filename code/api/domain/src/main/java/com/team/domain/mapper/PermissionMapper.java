package com.team.domain.mapper;

import com.team.domain.entity.PermissionEntity;
import com.team.infrastructure.base.MybatisBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 陈昭宇
 * @description Function 数据访问层
 * @date 2019-03-03
 */
public interface PermissionMapper extends MybatisBaseMapper<PermissionEntity> {

    List<PermissionEntity> selectAllByRoleIds(@Param("roleIds") List<String> roleIds);

    List<PermissionEntity> selectAllByUserId(@Param("userId") String userId);
}
