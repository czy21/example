package com.team.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.application.base.MybatisBaseServiceImpl;
import com.team.cooperated.exception.BusinessErrorCode;
import com.team.cooperated.exception.BusinessException;
import com.team.application.service.UserRoleService;
import com.team.application.service.UserService;
import com.team.domain.entity.UserRoleEntity;
import com.team.domain.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserRoleServiceImpl extends MybatisBaseServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {

    @Resource
    private UserService userService;

    private QueryWrapper<UserRoleEntity> queryByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "用户Id不能为空");
        }
        QueryWrapper<UserRoleEntity> query = new QueryWrapper<>();
        query.lambda().eq(UserRoleEntity::getUserId, userId);
        return query;
    }

    @Override
    public List<String> getRolesByUserId(String userId) {
        return super.selectAll(queryByUserId(userId)).stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String insertOrUpdateUserRole(String userId, String[] userRoleIds) {
        super.baseMapper.delete(queryByUserId(userId));
        if (!ObjectUtils.isEmpty(userRoleIds)) {
            Arrays.asList(userRoleIds).forEach(t -> {
                UserRoleEntity userRole = new UserRoleEntity();
                userRole.setUserId(userId);
                userRole.setRoleId(t);
                super.insert(userRole);
            });
        }
        return userService.selectOneById(userId).getUserName();
    }


}
