package com.team.entity.map;

import com.team.entity.page.PageModel;
import com.team.entity.system.Department;
import com.team.entity.dto.DepartmentDto;
import com.team.entity.dto.PageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = CentralConfig.class)
public interface DepartmentMap {
    DepartmentDto toDepartmentDto(Department department);

    Department toDepartment(DepartmentDto dto);

    @Mappings({
            @Mapping(source = "pageIndex", target = "page.pageIndex"),
            @Mapping(source = "pageSize", target = "page.pageSize"),
            @Mapping(source = "total", target = "page.total")})
    PageDto<DepartmentDto> toPageDto(PageModel<Department> page);
}
