package com.team.application.automap;

import com.team.application.model.dto.PageDTO;
import com.team.application.model.page.PageModel;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

public interface BaseAutoMap<S, T> {

    T mapToTarget(S entity);

    S mapToSource(T dto);

    List<T> mapToTargets(List<S> entities);

    List<S> mapToSources(List<T> dtos);

    @Mappings(value = {
            @Mapping(source = "pageIndex", target = "page.pageIndex"),
            @Mapping(source = "pageSize", target = "page.pageSize"),
            @Mapping(source = "total", target = "page.total")
    })
    PageDTO<T> mapToPageTarget(PageModel<S> pageModel);


}
