package com.team.system;

import com.team.universal.BaseDao;
import com.team.entity.system.UserRole;

import java.util.List;

/**
 * @Description UserRole 数据访问层
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserRoleDao extends BaseDao<UserRole> {

    List<String> getRolesByUserId(String userId);
}
