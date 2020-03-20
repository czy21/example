package com.team.application.service.impl;

import com.team.application.base.MybatisBaseServiceImpl;
import com.team.cooperated.exception.BusinessErrorCode;
import com.team.cooperated.exception.BusinessException;
import com.team.application.model.automap.RoleAutoMap;
import com.team.application.model.dto.RoleDTO;
import com.team.application.service.RolePermissionService;
import com.team.application.service.RoleService;
import com.team.domain.entity.PermissionEntity;
import com.team.domain.entity.RoleEntity;
import com.team.domain.mapper.PermissionMapper;
import com.team.domain.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends MybatisBaseServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Resource
    private RoleAutoMap roleMap;

    @Resource
    private RolePermissionService roleFunctionService;
    @Autowired
    private PermissionMapper functionRepository;

    @Override
    public RoleDTO insertRole(RoleDTO dto) {
        if (StringUtils.isEmpty(dto.getName())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_NAME, "角色名称不能为空");
        }
        if (super.selectOne(RoleEntity::getName, dto.getName()) != null) {
            throw new BusinessException(BusinessErrorCode.EXIST_NAME, "角色名称已存在");
        }
        return roleMap.mapToDto(super.insertAndGet(roleMap.mapToEntity(dto)));
    }

    @Override
    public RoleDTO editRole(RoleDTO dto) {
        if (StringUtils.isEmpty(dto.getId())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "角色Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getName())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_NAME, "角色名称不能为空");
        }
        return roleMap.mapToDto(super.updateAndGet(roleMap.mapToEntity(dto)));
    }

    @Override
    public List<String> getFunctionsByRoleId(String roleId) {
        List<String> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        return functionRepository.selectAllByRoleIds(roleIds).stream().map(PermissionEntity::getKey).collect(Collectors.toList());
    }

    @Override
    public String updateRoleFuncByRoleId(String roleId, String[] roleFuncIds) {
        return roleFunctionService.insertOrUpdateRoleFunc(roleId, roleFuncIds);
    }

}
