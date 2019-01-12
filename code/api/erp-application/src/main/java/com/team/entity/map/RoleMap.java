package com.team.entity.map;

import com.team.entity.page.PageModel;
import com.team.entity.mybatis.system.Role;
import com.team.entity.dto.PageDto;
import com.team.entity.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = CentralConfig.class)
public interface RoleMap {

    RoleDto toRoleDto(Role role);

    Role toRole(RoleDto dto);

    @Mappings({
            @Mapping(source = "pageIndex", target = "page.pageIndex"),
            @Mapping(source = "pageSize", target = "page.pageSize"),
            @Mapping(source = "total", target = "page.total")})
    PageDto<RoleDto> toPageDto(PageModel<Role> page);
}
