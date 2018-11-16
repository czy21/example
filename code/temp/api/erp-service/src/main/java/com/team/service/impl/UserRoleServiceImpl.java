package com.team.service.impl;

import com.team.entity.system.UserRole;
import com.team.service.UserRoleService;
import com.team.system.UserRoleDao;
import com.team.universal.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description UserRole 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

    @Resource
    private UserRoleDao userRoleDao;

    @Override
    public List<String> getRolesByUserId(String userId) {
        return userRoleDao.getRolesByUserId(userId);
    }
}
