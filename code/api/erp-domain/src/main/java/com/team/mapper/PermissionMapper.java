package com.team.mapper;

import com.team.entity.PermissionEntity;
import com.team.entity.mybatis.system.Function;
import com.team.mapper.base.MybatisBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 陈昭宇
 * @description Function 数据访问层
 * @date 2019-03-03
 */
public interface PermissionMapper extends MybatisBaseMapper<PermissionEntity> {

    List<PermissionEntity> getFunctionsByRoleIds(@Param("roleIds") List<Long> roleIds);

    List<PermissionEntity> getFunctionsByUserId(@Param("userId") Long userId);
}
