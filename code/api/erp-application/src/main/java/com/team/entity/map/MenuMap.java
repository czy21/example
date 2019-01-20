package com.team.entity.map;

import com.team.entity.dto.UserDto;
import com.team.entity.mybatis.system.User;
import com.team.extension.entity.MenuExtension;
import com.team.entity.page.PageModel;
import com.team.util.PageUtil;
import com.team.entity.mybatis.system.Menu;
import com.team.entity.dto.MenuDto;
import com.team.entity.dto.PageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(config = CentralConfig.class)
public interface MenuMap extends BaseMap<Menu, MenuDto>{
    List<MenuExtension.Node> toMenuTree(List<Menu> menus);
}
