package com.team.entity.map;

import com.team.entity.dto.PageDTO;
import com.team.entity.page.PageModel;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

public interface BaseAutoMap<TEntity, TDto> {

    TDto mapToDto(TEntity entity);

    TEntity mapToEntity(TDto dto);

    List<TDto> mapToDtos(List<TEntity> entities);

    @Mappings({
            @Mapping(source = "pageIndex", target = "page.pageIndex"),
            @Mapping(source = "pageSize", target = "page.pageSize"),
            @Mapping(source = "total", target = "page.total")})
    PageDTO<TDto> mapToPageDto(PageModel<TEntity> pageModel);


}
