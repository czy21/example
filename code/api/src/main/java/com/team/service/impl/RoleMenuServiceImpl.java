package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.dao.RoleMenuDao;
import com.team.dao.UserDao;
import com.team.entity.po.Menu;
import com.team.entity.po.RoleMenu;
import com.team.service.MenuService;
import com.team.service.RoleMenuService;
import com.team.core.universal.BaseServiceImpl;
import com.team.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
