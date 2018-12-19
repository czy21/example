package com.team.entity.map;

import com.team.entity.dto.LogDto;
import com.team.entity.dto.PageDto;
import com.team.entity.dto.UserDto;
import com.team.entity.page.PageModel;
import com.team.entity.page.PageParams;
import com.team.entity.system.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
@Mapper(config = CentralConfig.class)
public interface LogMap {
    @Mappings({
            @Mapping(source = "pageIndex", target = "page.pageIndex"),
            @Mapping(source = "pageSize", target = "page.pageSize"),
            @Mapping(source = "total", target = "page.total")})
    PageDto<LogDto> toPageDto(PageModel<Log> page);
}
