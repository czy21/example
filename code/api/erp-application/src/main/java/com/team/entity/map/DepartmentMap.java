package com.team.entity.map;

import com.team.entity.dto.DepartmentDto;
import com.team.entity.mybatis.system.Department;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface DepartmentMap extends BaseMap<Department, DepartmentDto> {

}
