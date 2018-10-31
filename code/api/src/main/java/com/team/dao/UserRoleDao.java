package com.team.dao;

import com.team.entity.po.UserRole;
import com.team.core.universal.BaseDao;

import java.util.List;

/**
 * @Description UserRole 数据访问层
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserRoleDao extends BaseDao<UserRole> {

    List<String> getRolesByUserId(String userId);
}
