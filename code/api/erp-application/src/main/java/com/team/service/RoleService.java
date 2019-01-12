package com.team.service;

import com.team.core.universal.MybatisBaseService;
import com.team.entity.dto.RoleDto;
import com.team.entity.mybatis.system.Role;

/**
 * @Description Role 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleService extends MybatisBaseService<Role> {

    RoleDto insertRole(RoleDto dto);

    RoleDto editRole(RoleDto dto);
}
