package com.team.controller;


import com.team.core.mvc.Pocket;
import com.team.entity.po.Menu;
import com.team.entity.vo.MenuDto;
import com.team.entity.vo.PageDto;
import com.team.model.SearchPermissionModel;
import com.team.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description Menu 前端控制器
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@RestController
@RequestMapping("api/menu")
@Api(tags = "菜单权限操作接口")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("load")
    @Pocket(entity = {Menu.class})
    @ApiOperation(value = "加载菜单(权限)列表")
    public PageDto<MenuDto> Load(SearchPermissionModel search) {
        return menuService.getMenuAndPermissionPageListBy(search);
    }

    @PostMapping("search")
    public PageDto<MenuDto> Search(SearchPermissionModel search) {
        return menuService.getMenuAndPermissionPageListBy(search);
    }

    @PostMapping("add")
    public MenuDto Add(MenuDto dto) {
        return menuService.insertMenu(dto);
    }
}