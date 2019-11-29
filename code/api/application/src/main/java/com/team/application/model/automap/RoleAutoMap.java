package com.team.application.model.automap;

import com.team.domain.entity.RoleEntity;
import com.team.application.model.dto.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface RoleAutoMap extends BaseAutoMap<RoleEntity, RoleDTO> {

}
