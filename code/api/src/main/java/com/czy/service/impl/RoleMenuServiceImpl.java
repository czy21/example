package com.czy.service.impl;

import com.czy.dao.RoleMenuDao;
import com.czy.entity.po.RoleMenu;
import com.czy.service.RoleMenuService;
import com.czy.core.universal.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description RoleMenu 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu> implements RoleMenuService {

    @Resource
    private RoleMenuDao roleMenuDao;

    @Override
    public List<String> getPermissionsByUserId(String userId) {
        return roleMenuDao.getPermissionsByUserId(userId);
    }
}
