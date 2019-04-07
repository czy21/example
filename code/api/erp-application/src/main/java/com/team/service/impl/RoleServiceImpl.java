package com.team.service.impl;

import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.entity.dto.RoleDto;
import com.team.entity.map.RoleMap;
import com.team.entity.mybatis.system.Function;
import com.team.entity.mybatis.system.Menu;
import com.team.entity.mybatis.system.Role;
import com.team.exception.BusinessException;
import com.team.exception.BusinessErrorCode;
import com.team.repository.mybatis.system.FunctionRepository;
import com.team.repository.mybatis.system.MenuRepository;
import com.team.service.RoleFunctionService;
import com.team.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description Role 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class RoleServiceImpl extends MybatisBaseServiceImpl<Role> implements RoleService {

    @Resource
    private RoleMap roleMap;

    @Resource
    private RoleFunctionService roleFunctionService;
    @Autowired
    private FunctionRepository functionRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public RoleDto insertRole(RoleDto dto) {
        if (StringUtils.isEmpty(dto.getRoleName())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_NAME, "角色名称不能为空");
        }
        if (super.SelectBy(Role::getRoleName, dto.getRoleName()) != null) {
            throw new BusinessException(BusinessErrorCode.EXIST_NAME, "角色名称已存在");
        }
        return roleMap.mapToDto(super.InsertAndGetEntity(roleMap.mapToEntity(dto)));
    }

    @Override
    public RoleDto editRole(RoleDto dto) {
        if (StringUtils.isEmpty(dto.getRoleId())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "角色Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getRoleName())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_NAME, "角色名称不能为空");
        }
        return roleMap.mapToDto(super.UpdateAndGetEntity(roleMap.mapToEntity(dto)));
    }

    @Override
    public List<Long> getMenusByRoleId(Long roleId) {
        if (StringUtils.isEmpty(roleId)) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "角色Id不能为空");
        }
        return menuRepository.getMenusByRoleId(roleId).stream().filter(t -> !t.getUrl().equals("#")).map(Menu::getMenuId).collect(Collectors.toList());
    }

    @Override
    public List<Long> getFunctionsByRoleId(Long roleId) {
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        return functionRepository.getFunctionsByRoleIds(roleIds).stream().map(Function::getFunctionId).collect(Collectors.toList());
    }

    @Override
    public String updateRoleFuncByRoleId(Long roleId, Long[] roleFuncIds) {
        return roleFunctionService.insertOrUpdateRoleFunc(roleId, roleFuncIds);
    }

}
