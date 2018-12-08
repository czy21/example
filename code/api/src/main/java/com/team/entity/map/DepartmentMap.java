package com.team.entity.map;

import com.team.core.universal.PageModel;
import com.team.entity.po.Department;
import com.team.entity.vo.DepartmentDto;
import com.team.entity.vo.PageDto;
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
