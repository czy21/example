package com.team.entity.map;

import com.team.core.util.TreeUtil;
import com.team.entity.po.Menu;
import com.team.entity.vo.MenuDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMap {

    List<MenuDto> toMenuDtos(List<Menu> menus);

    List<TreeUtil.Node> toMenuTree(List<Menu> menus);
}
