package com.team.application.model.automap;

import com.team.domain.entity.PermissionEntity;
import com.team.application.model.dto.PermissionDTO;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface PermissionAutoAutoMap extends BaseAutoMap<PermissionEntity, PermissionDTO> {
}
