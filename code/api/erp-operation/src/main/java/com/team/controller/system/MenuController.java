package com.team.controller.system;


import com.team.core.annotations.Pocket;
import com.team.entity.dto.MenuDto;
import com.team.entity.dto.PageDto;
import com.team.entity.mybatis.system.Menu;
import com.team.model.SearchMenuModel;
import com.team.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @Pocket(entity = Menu.class, obtainTree = true)
    @ApiOperation(value = "加载菜单列表")
    @RequiresPermissions("SearchMenu")
    public PageDto<MenuDto> Load(SearchMenuModel search) {
        return menuService.getMenuPageListBy(search);
    }

    @PostMapping("search")
    @ApiOperation(value = "查询菜单列表")
    @RequiresPermissions("SearchMenu")
    public PageDto<MenuDto> Search(SearchMenuModel search) {
        return menuService.getMenuPageListBy(search);
    }

    @PostMapping("add")
    @ApiOperation(value = "添加菜单信息")
    @RequiresPermissions("AddMenu")
    public MenuDto Add(MenuDto dto) {
        return menuService.insertMenu(dto);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改菜单信息")
    @RequiresPermissions("EditMenu")
    public MenuDto Edit(MenuDto dto) {
        return menuService.editMenu(dto);
    }

}