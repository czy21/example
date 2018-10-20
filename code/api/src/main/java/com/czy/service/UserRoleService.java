package com.czy.service;

import com.czy.entity.po.UserRole;
import com.czy.core.universal.BaseService;

import java.util.List;

/**
 * @Description UserRole 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserRoleService extends BaseService<UserRole> {

    List<String> getRolesByUserId(String userId);

}
