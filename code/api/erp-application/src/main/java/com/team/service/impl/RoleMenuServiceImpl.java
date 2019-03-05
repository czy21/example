package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.entity.mybatis.system.RoleMenu;
import com.team.exception.BusinessException;
import com.team.exception.ErrorCode;
import com.team.extension.ArrayExtension;
import com.team.service.RoleMenuService;
import com.team.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @Description RoleMenu 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class RoleMenuServiceImpl extends MybatisBaseServiceImpl<RoleMenu> implements RoleMenuService {

    @Resource
    private RoleService roleService;

    @Override
    @Transactional
    public String insertOrUpdateRoleMenu(Long roleId, Long[] roleMenuIds) {
        if (StringUtils.isEmpty(roleId)) {
            throw new BusinessException(ErrorCode.ID_NO_NULL, "角色Id不能为空");
        }
        QueryWrapper<RoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(RoleMenu::getRoleId, roleId);
        super.mybatisBaseRepository.delete(query);
        if (!ArrayExtension.isEmpty(roleMenuIds)) {
            Arrays.asList(roleMenuIds).forEach(t -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(t);
                super.Insert(roleMenu);
            });
        }
        return roleService.SelectById(roleId).getRoleName();
    }
}
