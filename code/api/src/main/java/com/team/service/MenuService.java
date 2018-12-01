package com.team.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.team.core.universal.BaseService;
import com.team.core.universal.PageModel;
import com.team.core.util.PageUtil;
import com.team.entity.po.Menu;
import com.team.entity.vo.MenuDto;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.PermissionDto;
import com.team.model.SearchPermissionModel;

import java.util.List;

/**
 * @Description Menu 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface MenuService extends BaseService<Menu> {

    PageDto<MenuDto> getMenuAndPermissionPageListBy(SearchPermissionModel search);

    MenuDto insertMenu(MenuDto dto);

    MenuDto editMenu(MenuDto dto);

    Integer deleteMenu(String menuId);

    Boolean batchInsertPermission(List<PermissionDto> dtos);

}
