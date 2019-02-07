package com.team.entity.map;

import com.team.extension.MenuExtension;
import com.team.entity.mybatis.system.Menu;
import com.team.entity.dto.MenuDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralConfig.class)
public interface MenuMap extends BaseMap<Menu, MenuDto>{
    List<MenuExtension.Node> toMenuTree(List<Menu> menus);
}
