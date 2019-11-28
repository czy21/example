package com.team.entity.map;

import com.team.domain.entity.PermissionEntity;
import com.team.entity.dto.PermissionDTO;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface PermissionAutoAutoMap extends BaseAutoMap<PermissionEntity, PermissionDTO> {
}
