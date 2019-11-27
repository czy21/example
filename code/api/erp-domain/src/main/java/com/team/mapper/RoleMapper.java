package com.team.mapper;

import com.team.entity.RoleEntity;
import com.team.infrastructure.MybatisBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description Role 数据访问层
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleMapper extends MybatisBaseMapper<RoleEntity> {

    List<RoleEntity> getRolesByLoginName(@Param("loginName") String loginName);

}
