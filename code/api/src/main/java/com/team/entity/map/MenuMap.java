package com.team.entity.map;

import com.team.core.extension.entity.MenuExtension;
import com.team.core.universal.PageModel;
import com.team.core.util.PageUtil;
import com.team.entity.po.Menu;
import com.team.entity.vo.MenuDto;
import com.team.entity.vo.PageDto;
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
