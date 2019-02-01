package com.team.entity.map;

import com.team.entity.dto.LogDto;
import com.team.entity.mongo.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralConfig.class)
public interface LogMap extends BaseMap<Log, LogDto> {

    @Mapping(source = "operator.userName",target = "operatorName")
    LogDto mapToDto(Log entity);

}
