package com.team.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.application.core.universal.MybatisBaseServiceImpl;
import com.team.application.exception.BusinessErrorCode;
import com.team.application.exception.BusinessException;
import com.team.application.service.RolePermissionService;
import com.team.application.service.RoleService;
import com.team.domain.entity.RolePermissionEntity;
import com.team.domain.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;

@Service
public class RolePermissionServiceImpl extends MybatisBaseServiceImpl<RolePermissionMapper, RolePermissionEntity> implements RolePermissionService {

    @Resource
    private RoleService roleService;

    @Override
    public String insertOrUpdateRoleFunc(String roleId, String[] roleFuncIds) {
        if (StringUtils.isEmpty(roleId)) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "角色Id不能为空");
        }
        QueryWrapper<RolePermissionEntity> query = new QueryWrapper<>();
        query.lambda().eq(RolePermissionEntity::getRoleId, roleId);
        super.baseMapper.delete(query);
        if (!ObjectUtils.isEmpty(roleFuncIds)) {
            Arrays.asList(roleFuncIds).forEach(t -> {
                RolePermissionEntity roleFunction = new RolePermissionEntity();
                roleFunction.setRoleId(roleId);
                roleFunction.setPermissionKey(t);
                super.insert(roleFunction);
            });
        }
        return roleService.selectOneById(roleId).getName();
    }
}
