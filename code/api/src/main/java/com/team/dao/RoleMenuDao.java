package com.team.dao;

import com.team.entity.po.Menu;
import com.team.entity.po.RoleMenu;
import com.team.core.universal.BaseDao;

import java.util.List;

/**
 * @Description RoleMenu 数据访问层
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleMenuDao extends BaseDao<RoleMenu> {

    List<String> getPermissionsByUserId(String userId);

    List<Menu> getMenusByUserId(String userId);
}
