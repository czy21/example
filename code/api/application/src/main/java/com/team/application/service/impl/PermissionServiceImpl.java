package com.team.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.application.base.MybatisBaseServiceImpl;
import com.team.application.exception.BusinessErrorCode;
import com.team.application.exception.BusinessException;
import com.team.application.model.automap.PermissionAutoAutoMap;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.PermissionDTO;
import com.team.application.model.vo.SearchFunctionModel;
import com.team.application.service.PermissionService;
import com.team.domain.entity.PermissionEntity;
import com.team.domain.mapper.PermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class PermissionServiceImpl extends MybatisBaseServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {

    @Resource
    private PermissionAutoAutoMap functionMap;


    private void verifyFunctionNameCode(PermissionDTO dto) {
        if (StringUtils.isEmpty(dto.getFunctionName())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_NAME, "权限名称不能为空");
        }
        if (StringUtils.isEmpty(dto.getFunctionCode())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_VALUE, "权限值不能为空");
        }
    }

    @Override
    public PermissionDTO insertFunction(PermissionDTO dto) {
        verifyFunctionNameCode(dto);
        return functionMap.mapToDto(super.insertAndGet(functionMap.mapToEntity(dto)));
    }

    @Override
    public PermissionDTO editFunction(PermissionDTO dto) {
        if (StringUtils.isEmpty(dto.getFunctionId())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "权限Id不能为空");
        }
        verifyFunctionNameCode(dto);
        return functionMap.mapToDto(super.updateAndGet(functionMap.mapToEntity(dto)));
    }

    @Override
    public PageDTO<PermissionDTO> getFunctionPageListBy(SearchFunctionModel search) {
        QueryWrapper<PermissionEntity> query = new QueryWrapper<>();
        if (!StringUtils.isEmpty(search.getFunctionName())) {
            query.lambda().like(PermissionEntity::getName, search.getFunctionName());
        }
        if (!StringUtils.isEmpty(search.getFunctionCode())) {
            query.lambda().like(PermissionEntity::getName, search.getFunctionCode());
        }
        return functionMap.mapToPageDto(super.selectAll(search.getPageIndex(), search.getPageSize(), query));
    }
}
