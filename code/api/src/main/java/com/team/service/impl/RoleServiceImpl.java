package com.team.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.team.core.exception.ErrorCode;
import com.team.core.exception.WebException;
import com.team.core.extension.StringExtension;
import com.team.entity.map.RoleMap;
import com.team.entity.po.Role;
import com.team.entity.vo.RoleDto;
import com.team.service.RoleService;
import com.team.core.universal.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
        if (super.SelectBy("RoleName", dto.getRoleName()) != null) {
            throw new WebException(ErrorCode.NAME_EXIST, "角色名称已存在");
        }
        return roleMap.toRoleDto(super.InsertAndGetEntity(roleMap.toRole(dto)));
    }

    @Override
    public RoleDto editRole(RoleDto dto) {
        if (StringExtension.StringIsNullOrEmpty(dto.getRoleId())) {
            throw new WebException(ErrorCode.ID_NO_EXIST, "角色Id不能为空");
        }
        if (StringExtension.StringIsNullOrEmpty(dto.getRoleName())) {
            throw new WebException(ErrorCode.NAME_NO_EXIST, "角色名称不能为空");
        }
        return roleMap.toRoleDto(super.UpdateAndGetEntity(roleMap.toRole(dto)));
    }

}
