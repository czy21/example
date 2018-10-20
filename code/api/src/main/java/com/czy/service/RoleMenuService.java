package com.czy.service;

import com.czy.entity.po.RoleMenu;
import com.czy.core.universal.BaseService;

import java.util.List;

/**
 * @Description RoleMenu 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleMenuService extends BaseService<RoleMenu> {

    List<String> getPermissionsByUserId(String userId);
}
