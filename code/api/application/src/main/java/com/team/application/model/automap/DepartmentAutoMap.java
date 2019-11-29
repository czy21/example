package com.team.application.model.automap;

import com.team.domain.entity.DepartmentEntity;
import com.team.application.model.dto.DepartmentDTO;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface DepartmentAutoMap extends BaseAutoMap<DepartmentEntity, DepartmentDTO> {

}
