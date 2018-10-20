package com.czy.service.impl;

import com.czy.dao.UserRoleDao;
import com.czy.entity.po.UserRole;
import com.czy.service.UserRoleService;
import com.czy.core.universal.BaseServiceImpl;
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
