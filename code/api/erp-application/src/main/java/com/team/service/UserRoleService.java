package com.team.service;

import com.team.core.universal.MybatisBaseService;
import com.team.entity.mybatis.system.UserRole;

import java.util.List;

/**
 * @Description UserRole 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserRoleService extends MybatisBaseService<UserRole> {

    List<Long> getRolesByUserId(Long userId);

    String insertOrUpdateUserRole(Long userId, Long[] userRoleIds);
}
