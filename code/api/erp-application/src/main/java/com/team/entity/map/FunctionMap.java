package com.team.entity.map;

import com.team.entity.dto.FunctionDto;
import com.team.entity.mybatis.system.Function;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface FunctionMap extends BaseMap<Function, FunctionDto> {
}
