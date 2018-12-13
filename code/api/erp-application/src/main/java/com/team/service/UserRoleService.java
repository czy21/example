package com.team.service;

import com.team.core.universal.BaseService;
import com.team.entity.system.UserRole;

import java.util.List;

/**
 * @Description UserRole 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserRoleService extends BaseService<UserRole> {

    List<String> getRolesByUserId(String userId);

    String insertOrUpdateUserRole(String userId, String[] userRoleIds);
}