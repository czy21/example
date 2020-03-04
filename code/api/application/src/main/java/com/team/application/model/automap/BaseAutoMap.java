package com.team.application.model.automap;

import com.team.application.model.dto.PageDTO;
import com.team.application.model.page.PageModel;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

public interface BaseAutoMap<TEntity, TDto> {

    TDto mapToDto(TEntity entity);

    TEntity mapToEntity(TDto dto);

    List<TDto> mapToDtos(List<TEntity> entities);

    List<TEntity> mapToEntities(List<TDto> dtos);

    @Mappings({
            @Mapping(source = "pageIndex", target = "page.pageIndex"),
            @Mapping(source = "pageSize", target = "page.pageSize"),
            @Mapping(source = "total", target = "page.total")})
    PageDTO<TDto> mapToPageDto(PageModel<TEntity> pageModel);


}
