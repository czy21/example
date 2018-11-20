package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.core.exception.ErrorCode;
import com.team.core.exception.WebException;
import com.team.core.extension.StringExtension;
import com.team.dao.UserRoleDao;
import com.team.entity.po.UserRole;
import com.team.service.UserRoleService;
import com.team.core.universal.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    @Transactional
    public Boolean insertOrUpdateUserRole(String userId, List<String> userRoleIds) {
        if (StringExtension.StringIsNullOrEmpty(userId)) {
            throw new WebException(ErrorCode.ID_NO_EXIST, "用户Id不能为空");
        }
        QueryWrapper<UserRole> userRoleWra = new QueryWrapper<>();
        userRoleWra.eq("UserId", userId);
        super.baseDao.delete(new QueryWrapper<UserRole>().eq("UserId", userId));
        userRoleIds.forEach(t -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(t);
            super.Insert(userRole);
        });
        return true;
    }
}
