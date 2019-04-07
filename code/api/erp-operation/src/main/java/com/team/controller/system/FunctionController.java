package com.team.controller.system;

import com.team.core.annotations.Pocket;
import com.team.entity.dto.FunctionDto;
import com.team.entity.dto.PageDto;
import com.team.entity.mybatis.system.Menu;
import com.team.model.SearchFunctionModel;
import com.team.service.FunctionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈昭宇
 * @description Function 前端控制器
 * @date 2019-03-09
 */
@RestController
@RequestMapping("api/function")
@Api(tags = "Function", description = "权限操作接口")
public class FunctionController {

    @Autowired
    private FunctionService functionService;

    @GetMapping("load")
    @Pocket(entity = Menu.class)
    @ApiOperation(value = "加载权限列表")
    @PreAuthorize("hasAuthority('SearchFunc')")
    public PageDto<FunctionDto> Load(SearchFunctionModel search) {
        return functionService.getFunctionPageListBy(search);
    }

    @PostMapping("search")
    @ApiOperation(value = "查询权限列表")
    @PreAuthorize("hasAuthority('SearchFunc')")
    public PageDto<FunctionDto> Search(SearchFunctionModel search) {
        return functionService.getFunctionPageListBy(search);
    }

    @PostMapping("add")
    @ApiOperation(value = "添加权限信息")
    @PreAuthorize("hasAuthority('AddFunc')")
    public FunctionDto Add(FunctionDto dto) {
        return functionService.insertFunction(dto);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改权限信息")
    @PreAuthorize("hasAuthority('EditFunc')")
    public FunctionDto Edit(FunctionDto dto) {
        return functionService.editFunction(dto);
    }
}
