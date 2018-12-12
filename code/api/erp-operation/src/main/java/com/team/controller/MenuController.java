package com.team.controller;


import com.alibaba.fastjson.JSON;
import com.team.core.configure.Pocket;
import com.team.entity.dto.MenuDto;
import com.team.entity.dto.PageDto;
import com.team.entity.dto.PermissionDto;
import com.team.entity.system.Menu;
import com.team.model.SearchPermissionModel;
import com.team.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "添加菜单或权限信息")
    public MenuDto Add(MenuDto dto) {
        return menuService.insertMenu(dto);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改菜单或权限信息")
    public MenuDto Edit(MenuDto dto) {
        return menuService.editMenu(dto);
    }

    @DeleteMapping("delete")
    @ApiOperation(value = "删除菜单或权限信息")
    public Integer Delete(String menuId) {
        return menuService.deleteMenu(menuId);
    }

    @PostMapping(value = "batchAddAction")
    @ApiOperation(value = "批量添加权限")
    public Boolean BatchAddAction(String permissions) {
        return menuService.batchInsertPermission(JSON.parseArray(permissions, PermissionDto.class));
    }
}