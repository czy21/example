package com.team.entity.map;

import com.team.core.universal.PageModel;
import com.team.core.util.PageUtil;
import com.team.core.util.TreeUtil;
import com.team.entity.po.Menu;
import com.team.entity.vo.MenuDto;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(config = CentralConfig.class)
public interface MenuMap {

    Menu toMenuDto(MenuDto dto);

    MenuDto toMenu(Menu menu);

    List<MenuDto> toMenuDtos(List<Menu> menus);

    List<TreeUtil.Node> toMenuTree(List<Menu> menus);

    @Mapping(source = "pageIndex", target = "page.pageIndex")
    @Mapping(source = "pageSize", target = "page.pageSize")
    @Mapping(source = "total", target = "page.total")
    PageDto<MenuDto> toPageDto(PageModel<Menu> page);

    @Mapping(source = "pageIndex", target = "page.pageIndex")
    @Mapping(source = "pageSize", target = "page.pageSize")
    @Mapping(source = "total", target = "page.total")
    PageDto<MenuDto> toPageDto(PageUtil<Menu> page);
}
