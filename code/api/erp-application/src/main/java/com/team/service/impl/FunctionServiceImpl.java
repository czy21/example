package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.entity.dto.FunctionDto;
import com.team.entity.dto.PageDto;
import com.team.entity.map.FunctionMap;
import com.team.entity.mybatis.system.Function;
import com.team.exception.BusinessException;
import com.team.exception.BusinessErrorCode;
import com.team.model.SearchFunctionModel;
import com.team.service.FunctionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author 陈昭宇
 * @description Function 服务实现类
 * @date 2019-03-05
 */
@Service
public class FunctionServiceImpl extends MybatisBaseServiceImpl<Function> implements FunctionService {

    @Resource
    private FunctionMap functionMap;


    private void verifyFunctionNameCode(FunctionDto dto) {
        if (StringUtils.isEmpty(dto.getFunctionName())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_NAME, "权限名称不能为空");
        }
        if (StringUtils.isEmpty(dto.getFunctionCode())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_VALUE, "权限值不能为空");
        }
    }

    @Override
    public FunctionDto insertFunction(FunctionDto dto) {
        verifyFunctionNameCode(dto);
        return functionMap.mapToDto(super.InsertAndGetEntity(functionMap.mapToEntity(dto)));
    }

    @Override
    public FunctionDto editFunction(FunctionDto dto) {
        if (StringUtils.isEmpty(dto.getFunctionId())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "权限Id不能为空");
        }
        verifyFunctionNameCode(dto);
        return functionMap.mapToDto(super.UpdateAndGetEntity(functionMap.mapToEntity(dto)));
    }

    @Override
    public PageDto<FunctionDto> getFunctionPageListBy(SearchFunctionModel search) {
        QueryWrapper<Function> query = new QueryWrapper<>();
        if (!StringUtils.isEmpty(search.getFunctionName())) {
            query.lambda().like(Function::getFunctionName, search.getFunctionName());
        }
        if (!StringUtils.isEmpty(search.getFunctionCode())) {
            query.lambda().like(Function::getFunctionCode, search.getFunctionCode());
        }
        return functionMap.mapToPageDto(super.SelectPageListBy(search.getPageIndex(), search.getPageSize(), query));
    }
}
