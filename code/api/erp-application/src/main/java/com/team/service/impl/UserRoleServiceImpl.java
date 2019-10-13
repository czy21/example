package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.exception.BusinessErrorCode;
import com.team.exception.BusinessException;
import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.entity.mybatis.system.UserRole;
import com.team.service.UserRoleService;
import com.team.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description UserRole 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class UserRoleServiceImpl extends MybatisBaseServiceImpl<UserRole> implements UserRoleService {

    @Resource
    private UserService userService;

    private QueryWrapper<UserRole> queryByUserId(Long userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "用户Id不能为空");
        }
        QueryWrapper<UserRole> query = new QueryWrapper<>();
        query.lambda().eq(UserRole::getUserId, userId);
        return query;
    }

    @Override
    public List<Long> getRolesByUserId(Long userId) {
        return super.SelectListBy(queryByUserId(userId)).stream().map(UserRole::getRoleId).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String insertOrUpdateUserRole(Long userId, Long[] userRoleIds) {
        super.mybatisBaseRepository.delete(queryByUserId(userId));
        if (!ObjectUtils.isEmpty(userRoleIds)) {
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
