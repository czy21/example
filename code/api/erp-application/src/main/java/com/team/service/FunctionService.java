package com.team.service;

import com.team.core.universal.MybatisBaseService;
import com.team.entity.dto.FunctionDto;
import com.team.entity.dto.PageDto;
import com.team.entity.mybatis.system.Function;
import com.team.model.SearchFunctionModel;

/**
 * @author 陈昭宇
 * @description Function 服务类
 * @date 2019-03-05
 */
public interface FunctionService extends MybatisBaseService<Function> {

    FunctionDto insertFunction(FunctionDto dto);

    FunctionDto editFunction(FunctionDto dto);

    PageDto<FunctionDto> getFunctionPageListBy(SearchFunctionModel search);
}
