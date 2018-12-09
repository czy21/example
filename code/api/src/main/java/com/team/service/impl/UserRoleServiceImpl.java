package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.core.exception.ErrorCode;
import com.team.core.exception.WebException;
import com.team.core.extension.ArrayExtension;
import com.team.core.universal.BaseServiceImpl;
import com.team.entity.po.UserRole;
import com.team.service.UserRoleService;
import com.team.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description UserRole 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

    @Resource
    private UserService userService;

    @Override
    public List<String> getRolesByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new WebException(ErrorCode.ID_NO_NULL, "用户Id不能为空");
        }
        List<String> roleIds = new ArrayList<>();
        QueryWrapper<UserRole> query = new QueryWrapper<>();
        query.lambda().eq(UserRole::getUserId, userId);
        super.SelectListBy(query).forEach(t -> roleIds.add(t.getRoleId()));
        return roleIds;
    }

    @Override
    @Transactional
    public String insertOrUpdateUserRole(String userId, String[] userRoleIds) {
        if (StringUtils.isEmpty(userId)) {
            throw new WebException(ErrorCode.ID_NO_NULL, "用户Id不能为空");
        }
        QueryWrapper<UserRole> query = new QueryWrapper<>();
        query.lambda().eq(UserRole::getUserId, userId);
        super.baseDao.delete(query);

        if (!ArrayExtension.isEmpty(userRoleIds)) {
            Arrays.asList(userRoleIds).forEach(t -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(t);
                super.Insert(userRole);
            });
        }
        return userService.SelectById(userId).getUserName();
    }
}
