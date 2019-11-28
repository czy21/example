package com.team.entity.map;

import com.team.entity.dto.LogDTO;
import com.team.domain.entity.LogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralConfig.class)
public interface LogAutoMap extends BaseAutoMap<LogEntity, LogDTO> {

    @Override
    @Mapping(source = "operator.userName",target = "operatorName")
    LogDTO mapToDto(LogEntity entity);

}
