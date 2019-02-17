package com.team.service;

import com.team.core.universal.MybatisBaseService;
import com.team.entity.mybatis.system.Menu;
import com.team.entity.mybatis.system.RoleMenu;

import java.util.List;

/**
 * @Description RoleMenu 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleMenuService extends MybatisBaseService<RoleMenu> {

    List<String> getPermissionsByUserId(Long userId);

    List<String> getPermissionOfValuesByUserId(Long userId);

    List<Menu> getMenusByUserId(Long userId);

    List<Long> getMenusByRoleId(Long roleId);

    List<Long> getPermissionsByRoleId(Long roleId);

    String insertOrUpdateRoleMenu(Long roleId, Long[] roleMenuIds);

}
