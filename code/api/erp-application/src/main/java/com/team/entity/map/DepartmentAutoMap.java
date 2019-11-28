package com.team.entity.map;

import com.team.domain.entity.DepartmentEntity;
import com.team.entity.dto.DepartmentDTO;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface DepartmentAutoMap extends BaseAutoMap<DepartmentEntity, DepartmentDTO> {

}
