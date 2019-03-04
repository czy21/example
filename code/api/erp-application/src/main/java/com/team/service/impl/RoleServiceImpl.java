package com.team.service.impl;

import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.entity.dto.RoleDto;
import com.team.entity.map.RoleMap;
import com.team.entity.mybatis.system.Role;
import com.team.exception.ErrorCode;
import com.team.exception.BusinessException;
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
public class RoleServiceImpl extends MybatisBaseServiceImpl<Role> implements RoleService {

    @Resource
    private RoleMap roleMap;

    @Override
    public RoleDto insertRole(RoleDto dto) {
        if (StringUtils.isEmpty(dto.getRoleName())) {
            throw new BusinessException(ErrorCode.NAME_NO_NULL, "角色名称不能为空");
        }
        if (super.SelectBy(Role::getRoleName, dto.getRoleName()) != null) {
            throw new BusinessException(ErrorCode.NAME_EXIST, "角色名称已存在");
        }
        return roleMap.mapToDto(super.InsertAndGetEntity(roleMap.mapToEntity(dto)));
    }

    @Override
    public RoleDto editRole(RoleDto dto) {
        if (StringUtils.isEmpty(dto.getRoleId())) {
            throw new BusinessException(ErrorCode.ID_NO_NULL, "角色Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getRoleName())) {
            throw new BusinessException(ErrorCode.NAME_NO_NULL, "角色名称不能为空");
        }
        return roleMap.mapToDto(super.UpdateAndGetEntity(roleMap.mapToEntity(dto)));
    }

}
