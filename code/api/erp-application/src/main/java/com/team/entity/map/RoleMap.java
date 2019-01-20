package com.team.entity.map;

import com.team.entity.dto.RoleDto;
import com.team.entity.mybatis.system.Role;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface RoleMap extends BaseMap<Role, RoleDto> {

}
