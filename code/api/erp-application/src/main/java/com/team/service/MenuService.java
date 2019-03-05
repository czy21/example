package com.team.service;

import com.team.core.universal.MybatisBaseService;
import com.team.entity.dto.MenuDto;
import com.team.entity.dto.PageDto;
import com.team.entity.mybatis.system.Menu;
import com.team.model.SearchPermissionModel;

/**
 * @Description Menu 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface MenuService extends MybatisBaseService<Menu> {

    PageDto<MenuDto> getMenuAndPermissionPageListBy(SearchPermissionModel search);

    MenuDto insertMenu(MenuDto dto);

    MenuDto editMenu(MenuDto dto);

    Integer deleteMenu(Long menuId);

}
