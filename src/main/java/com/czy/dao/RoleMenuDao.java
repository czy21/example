package com.czy.dao;

import com.czy.entity.po.RoleMenu;
import com.czy.core.universal.BaseDao;

import java.util.List;

/**
 * @Description RoleMenu 数据访问层
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleMenuDao extends BaseDao<RoleMenu> {

    List<String> getPermissionsByUserId(String userId);
}
