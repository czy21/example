package com.team.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.application.core.universal.MybatisBaseServiceImpl;
import com.team.domain.entity.RolePermissionEntity;
import com.team.application.exception.BusinessErrorCode;
import com.team.application.exception.BusinessException;
import com.team.application.service.RolePermissionService;
import com.team.application.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author 陈昭宇
 * @description RoleFunction 服务实现类
 * @date 2019-03-05
 */
@Service
public class RolePermissionServiceImpl extends MybatisBaseServiceImpl<RolePermissionEntity> implements RolePermissionService {

    @Resource
    private RoleService roleService;

    @Override
    public String insertOrUpdateRoleFunc(String roleId, String[] roleFuncIds) {
        if (StringUtils.isEmpty(roleId)) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "角色Id不能为空");
        }
        QueryWrapper<RolePermissionEntity> query = new QueryWrapper<>();
        query.lambda().eq(RolePermissionEntity::getRoleId, roleId);
        super.mybatisBaseRepository.delete(query);
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
