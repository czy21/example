package com.team.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.core.aop.AnnotationLog;
import com.team.core.extension.entity.MenuExtensions;
import com.team.core.mvc.Pocket;
import com.team.core.util.PageUtil;
import com.team.entity.map.MenuMap;
import com.team.entity.po.Menu;
import com.team.entity.vo.MenuDto;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.RoleDto;
import com.team.model.SearchPermissionModel;
import com.team.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description Menu 前端控制器
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuMap menuMap;

    @Autowired
    private MenuService menuService;

    @RequestMapping("load")
    @AnnotationLog(remark = "加载菜单(权限)列表")
    @Pocket(entity = {Menu.class})
    public PageDto<MenuDto> Load(SearchPermissionModel search) {
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.orderByDesc("IsMenu");
        return menuMap.toPageDto(menuService.SelectPageListBy(search.getPageIndex(), search.getPageSize(), query));
    }

    @PostMapping("search")
    public PageDto<MenuDto> Search(SearchPermissionModel search) {
        return menuMap.toPageDto(menuService.getMenuAndPermissionPageListBy(search));
    }

    @PostMapping("add")
    public MenuDto Add(MenuDto dto) {
        return menuMap.toMenuDto(menuService.InsertAndGetEntity(menuMap.toMenu(dto)));
    }
}