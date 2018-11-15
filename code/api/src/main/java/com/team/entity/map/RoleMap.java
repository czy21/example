package com.team.entity.map;

import com.team.core.universal.PageModel;
import com.team.entity.po.Role;
import com.team.entity.po.User;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.RoleDto;
import com.team.entity.vo.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(config = CentralConfig.class)
public interface RoleMap {
    @Mapping(source = "pageIndex", target = "page.pageIndex")
    @Mapping(source = "pageSize", target = "page.pageSize")
    @Mapping(source = "total", target = "page.total")
    PageDto<RoleDto> toPageDto(PageModel<Role> page);
}
