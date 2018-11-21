package com.team.service;

import com.team.entity.po.Role;
import com.team.core.universal.BaseService;
import com.team.entity.vo.RoleDto;

/**
 * @Description Role 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleService extends BaseService<Role> {

    RoleDto insertRole(RoleDto dto);

}
