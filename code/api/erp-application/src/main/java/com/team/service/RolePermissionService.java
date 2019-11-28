package com.team.service;

import com.team.core.universal.MybatisBaseService;
import com.team.domain.entity.RolePermissionEntity;

/**
 * @author 陈昭宇
 * @description RoleFunction 服务类
 * @date 2019-03-05
 */
public interface RolePermissionService extends MybatisBaseService<RolePermissionEntity> {


    String insertOrUpdateRoleFunc(String roleId, String[] roleFuncIds);

}
