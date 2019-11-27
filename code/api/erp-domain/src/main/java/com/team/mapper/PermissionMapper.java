package com.team.mapper;

import com.team.entity.PermissionEntity;
import com.team.infrastructure.MybatisBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 陈昭宇
 * @description Function 数据访问层
 * @date 2019-03-03
 */
public interface PermissionMapper extends MybatisBaseMapper<PermissionEntity> {

    List<PermissionEntity> selectPermissionByRoleIds(@Param("roleIds") List<String> roleIds);

    List<PermissionEntity> selectPermissionsByUserId(@Param("userId") String userId);
}
