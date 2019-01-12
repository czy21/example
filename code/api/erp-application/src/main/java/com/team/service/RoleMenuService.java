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

    List<String> getPermissionsByUserId(String userId);

    List<String> getPermissionOfValuesByUserId(String userId);

    List<Menu> getMenusByUserId(String userId);

    List<String> getMenusByRoleId(String roleId);

    List<String> getPermissionsByRoleId(String roleId);

    String insertOrUpdateRoleMenu(String roleId, String[] roleMenuIds);

}
