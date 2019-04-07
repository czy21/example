package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.entity.mybatis.system.RoleFunction;
import com.team.exception.BusinessException;
import com.team.exception.BusinessErrorCode;
import com.team.extension.ArrayExtension;
import com.team.service.RoleFunctionService;
import com.team.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author 陈昭宇
 * @description RoleFunction 服务实现类
 * @date 2019-03-05
 */
@Service
public class RoleFunctionServiceImpl extends MybatisBaseServiceImpl<RoleFunction> implements RoleFunctionService {

    @Resource
    private RoleService roleService;

    @Override
    public String insertOrUpdateRoleFunc(Long roleId, Long[] roleFuncIds) {
        if (StringUtils.isEmpty(roleId)) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "角色Id不能为空");
        }
        QueryWrapper<RoleFunction> query = new QueryWrapper<>();
        query.lambda().eq(RoleFunction::getRoleId, roleId);
        super.mybatisBaseRepository.delete(query);
        if (!ArrayExtension.isEmpty(roleFuncIds)) {
            Arrays.asList(roleFuncIds).forEach(t -> {
                RoleFunction roleFunction = new RoleFunction();
                roleFunction.setRoleId(roleId);
                roleFunction.setFunctionId(t);
                super.Insert(roleFunction);
            });
        }
        return roleService.SelectById(roleId).getRoleName();
    }
}
