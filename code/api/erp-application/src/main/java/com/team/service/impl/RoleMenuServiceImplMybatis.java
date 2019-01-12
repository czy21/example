package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.exception.ErrorCode;
import com.team.exception.WebException;
import com.team.extension.ArrayExtension;
import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.repository.mybatis.system.RoleMenuRepositoryMybatis;
import com.team.entity.mybatis.system.Menu;
import com.team.entity.mybatis.system.RoleMenu;
import com.team.service.MenuService;
import com.team.service.RoleMenuService;
import com.team.service.RoleService;
import com.team.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description RoleMenu 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class RoleMenuServiceImplMybatis extends MybatisBaseServiceImpl<RoleMenu> implements RoleMenuService {

    @Resource
    private RoleMenuRepositoryMybatis roleMenuDao;

    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleService roleService;

    @Override
    public List<String> getPermissionsByUserId(String userId) {
        return roleMenuDao.getMenusByUserId(userId, false).stream().map(Menu::getUrl).collect(Collectors.toList());
    }

    @Override
    public List<Menu> getMenusByUserId(String userId) {
        if (userService.SelectById(userId).getLoginName().equals("admin")) {
            QueryWrapper<Menu> query = new QueryWrapper<>();
            query.lambda().eq(Menu::getIsMenu, true);
            return menuService.SelectListBy(query);
        }

        return roleMenuDao.getMenusByUserId(userId, true);
    }

    @Override
    public List<String> getMenusByRoleId(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            throw new WebException(ErrorCode.ID_NO_NULL, "角色Id不能为空");
        }
        return roleMenuDao.selectList(new QueryWrapper<RoleMenu>().eq("RoleId", roleId)).stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
    }

    @Override
    public List<String> getPermissionsByRoleId(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            throw new WebException(ErrorCode.ID_NO_NULL, "角色Id不能为空");
        }
        return roleMenuDao.getMenusByRoleId(roleId, false).stream().map(Menu::getMenuId).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String insertOrUpdateRoleMenu(String roleId, String[] roleMenuIds) {
        if (StringUtils.isEmpty(roleId)) {
            throw new WebException(ErrorCode.ID_NO_NULL, "角色Id不能为空");
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

    @Override
    public List<String> getPermissionOfValuesByUserId(String userId) {
        String suffiex = "/api/";
        return roleMenuDao.getMenusByUserId(userId, false).stream().filter(t -> t.getUrl().startsWith(suffiex)).map(t -> t.getUrl().substring(suffiex.length())).collect(Collectors.toList());
    }
}
