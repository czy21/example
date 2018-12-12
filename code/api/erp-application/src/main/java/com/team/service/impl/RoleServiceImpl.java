package com.team.service.impl;

import com.team.core.universal.BaseServiceImpl;
import com.team.entity.dto.RoleDto;
import com.team.entity.map.RoleMap;
import com.team.entity.system.Role;
import com.team.exception.ErrorCode;
import com.team.exception.WebException;
import com.team.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @Description Role 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Resource
    private RoleMap roleMap;

    @Override
    public RoleDto insertRole(RoleDto dto) {
        if (StringUtils.isEmpty(dto.getRoleName())) {
            throw new WebException(ErrorCode.NAME_NO_NULL, "角色名称不能为空");
        }
        if (super.SelectBy(Role::getRoleName, dto.getRoleName()) != null) {
            throw new WebException(ErrorCode.NAME_EXIST, "角色名称已存在");
        }
        return roleMap.toRoleDto(super.InsertAndGetEntity(roleMap.toRole(dto)));
    }

    @Override
    public RoleDto editRole(RoleDto dto) {
        if (StringUtils.isEmpty(dto.getRoleId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "角色Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getRoleName())) {
            throw new WebException(ErrorCode.NAME_NO_NULL, "角色名称不能为空");
        }
        return roleMap.toRoleDto(super.UpdateAndGetEntity(roleMap.toRole(dto)));
    }

}
