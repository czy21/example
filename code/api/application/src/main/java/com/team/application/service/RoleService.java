package com.team.application.service;

import com.team.application.core.universal.MybatisBaseService;
import com.team.domain.entity.RoleEntity;
import com.team.application.model.dto.RoleDTO;

import java.util.List;

/**
 * @Description Role 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleService extends MybatisBaseService<RoleEntity> {

    RoleDTO insertRole(RoleDTO dto);

    RoleDTO editRole(RoleDTO dto);

    List<String> getFunctionsByRoleId(String roleId);

    String updateRoleFuncByRoleId(String roleId, String[] roleFuncIds);

}
