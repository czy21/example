package com.team.service;

import com.team.core.universal.MybatisBaseService;
import com.team.entity.mybatis.system.RoleMenu;

/**
 * @Description RoleMenu 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleMenuService extends MybatisBaseService<RoleMenu> {

    String insertOrUpdateRoleMenu(Long roleId, Long[] roleMenuIds);

}
