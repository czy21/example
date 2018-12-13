package com.team.entity.map;

import com.team.extension.entity.MenuExtension;
import com.team.entity.page.PageModel;
import com.team.util.PageUtil;
import com.team.entity.system.Menu;
import com.team.entity.dto.MenuDto;
import com.team.entity.dto.PageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(config = CentralConfig.class)
public interface MenuMap {

    Menu toMenu(MenuDto dto);

    MenuDto toMenuDto(Menu menu);

    List<MenuDto> toMenuDtos(List<Menu> menus);

    List<MenuExtension.Node> toMenuTree(List<Menu> menus);

    @Mappings({
            @Mapping(source = "pageIndex", target = "page.pageIndex"),
            @Mapping(source = "pageSize", target = "page.pageSize"),
            @Mapping(source = "total", target = "page.total")})
    PageDto<MenuDto> toPageDto(PageModel<Menu> page);

    @Mappings({
            @Mapping(source = "pageIndex", target = "page.pageIndex"),
            @Mapping(source = "pageSize", target = "page.pageSize"),
            @Mapping(source = "total", target = "page.total")})
    PageDto<MenuDto> toPageDto(PageUtil<Menu> page);
}
