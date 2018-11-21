package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.core.exception.ErrorCode;
import com.team.core.exception.WebException;
import com.team.core.extension.StringExtension;
import com.team.dao.RoleMenuDao;
import com.team.dao.UserDao;
import com.team.entity.po.Menu;
import com.team.entity.po.RoleMenu;
import com.team.entity.po.UserRole;
import com.team.service.MenuService;
import com.team.service.RoleMenuService;
import com.team.core.universal.BaseServiceImpl;
import com.team.service.RoleService;
import com.team.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description RoleMenu 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu> implements RoleMenuService {

    @Resource
    private RoleMenuDao roleMenuDao;

    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleService roleService;

    @Override
    public List<String> getPermissionsByUserId(String userId) {
        return roleMenuDao.getPermissionsByUserId(userId);
    }

    @Override
    public List<Menu> getMenusByUserId(String userId) {
        if (userService.SelectById(userId).getLoginName().equals("admin")) {
            QueryWrapper<Menu> wra = new QueryWrapper<>();
            wra.eq("IsMenu", true);
            return menuService.SelectListBy(wra);
        }
        return roleMenuDao.getMenusByUserId(userId);
    }

    @Override
    public List<String> getMenusByRoleId(String roleId) {
        if (StringExtension.StringIsNullOrEmpty(roleId)) {
            throw new WebException(ErrorCode.ID_NO_EXIST, "角色Id不能为空");
        }
        List<String> menuIds = new ArrayList<>();
        QueryWrapper<RoleMenu> roleMenuWra = new QueryWrapper<>();
        roleMenuWra.eq("RoleId", roleId);
        super.SelectListBy(roleMenuWra).forEach(t -> menuIds.add(t.getMenuId()));
        return menuIds;
    }

    @Override
    @Transactional
    public String insertOrUpdateRoleMenu(String roleId, String[] roleMenuIds) {
        if (StringExtension.StringIsNullOrEmpty(roleId)) {
            throw new WebException(ErrorCode.ID_NO_EXIST, "角色Id不能为空");
        }
        QueryWrapper<RoleMenu> roleMenuWra = new QueryWrapper<>();
        roleMenuWra.eq("RoleId", roleId);
        super.baseDao.delete(roleMenuWra);

        if (!StringExtension.ArrayIsNullOrEmpty(roleMenuIds)) {
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
