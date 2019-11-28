package com.team.service;

import com.team.core.universal.MybatisBaseService;
import com.team.domain.entity.UserRoleEntity;

import java.util.List;

/**
 * @Description UserRole 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserRoleService extends MybatisBaseService<UserRoleEntity> {

    List<String> getRolesByUserId(String userId);

    String insertOrUpdateUserRole(String userId, String[] userRoleIds);
}
