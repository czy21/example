package com.team.entity.map;

import com.team.entity.dto.LogDto;
import com.team.entity.dto.PageDto;
import com.team.entity.dto.UserDto;
import com.team.entity.page.PageModel;
import com.team.entity.page.PageParams;
import com.team.entity.mongo.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import java.util.List;
@Mapper(config = CentralConfig.class)
public interface LogMap<TEntity,TResult> {
//    @Mappings({
//            @Mapping(source = "getTotalElements()", target = "page.pageSize")})
//    PageDto<LogDto> toPageDto(Page<Log> page);
}
