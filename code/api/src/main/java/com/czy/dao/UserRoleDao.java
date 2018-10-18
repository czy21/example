package com.czy.dao;

import com.czy.entity.po.Role;
import com.czy.entity.po.UserRole;
import com.czy.core.universal.BaseDao;

import java.util.List;

/**
 * @Description UserRole 数据访问层
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserRoleDao extends BaseDao<UserRole> {

    List<String> getRolesByUserId(String userId);
}
