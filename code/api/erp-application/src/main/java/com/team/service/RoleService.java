package com.team.service;

import com.team.core.universal.MybatisBaseService;
import com.team.entity.dto.RoleDto;
import com.team.entity.mybatis.system.Role;

import java.util.List;

/**
 * @Description Role 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleService extends MybatisBaseService<Role> {

    RoleDto insertRole(RoleDto dto);

    RoleDto editRole(RoleDto dto);

    List<Long> getMenusByRoleId(Long roleId);

    List<Long> getFunctionsByRoleId(Long roleId);

    String updateRoleFuncByRoleId(Long roleId, Long[] roleFuncIds);

}
