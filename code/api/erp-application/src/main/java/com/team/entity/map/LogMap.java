package com.team.entity.map;

import com.team.entity.dto.LogDto;
import com.team.entity.LogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralConfig.class)
public interface LogMap extends BaseMap<LogEntity, LogDto> {

    @Mapping(source = "operator.userName",target = "operatorName")
    LogDto mapToDto(LogEntity entity);

}
