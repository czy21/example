package com.team.entity.map;

import com.team.entity.dto.LogDto;
import com.team.entity.mongo.Log;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface LogMap extends BaseMap<Log, LogDto> {


}
