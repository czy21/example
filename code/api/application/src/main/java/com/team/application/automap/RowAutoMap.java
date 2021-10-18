package com.team.application.automap;

import com.team.application.model.RowModel;
import com.team.domain.mongo.entity.FileColumnMappingEntity;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface RowAutoMap extends BaseAutoMap<FileColumnMappingEntity.Field, RowModel.ColModel> {
}
