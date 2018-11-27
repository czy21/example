package com.team.controller;


import com.alibaba.fastjson.JSON;
import com.team.core.mvc.Pocket;
import com.team.entity.po.Menu;
import com.team.entity.vo.MenuDto;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.PermissionDto;
import com.team.model.SearchPermissionModel;
import com.team.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description Menu 前端控制器
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@RestController
@RequestMapping("api/menu")
@Api(tags = "Menu", description = "菜单操作接口")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("load")
    @Pocket(entity = {Menu.class})
    @ApiOperation(value = "加载权限列表")
    public PageDto<MenuDto> Load(SearchPermissionModel search) {
        return menuService.getMenuAndPermissionPageListBy(search);
    }

    @PostMapping("search")
    @ApiOperation(value = "查询权限列表")
    public PageDto<MenuDto> Search(SearchPermissionModel search) {
        return menuService.getMenuAndPermissionPageListBy(search);
    }

    @PostMapping("add")
    @ApiOperation(value = "添加菜单或权限")
    public MenuDto Add(MenuDto dto) {
        return menuService.insertMenu(dto);
    }

    @PostMapping(value = "batchAddAction")
    public Object BatchAddAction(String permissions) {
        List<PermissionDto> dto = JSON.parseArray(permissions, PermissionDto.class);
        return dto;
    }
}