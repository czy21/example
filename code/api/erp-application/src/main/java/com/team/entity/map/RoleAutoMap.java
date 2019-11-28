package com.team.entity.map;

import com.team.domain.entity.RoleEntity;
import com.team.entity.dto.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface RoleAutoMap extends BaseAutoMap<RoleEntity, RoleDTO> {

}
