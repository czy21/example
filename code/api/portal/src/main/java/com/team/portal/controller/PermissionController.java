package com.team.portal.controller;

import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.PermissionDTO;
import com.team.application.model.vo.SearchFunctionModel;
import com.team.application.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("function")
public class PermissionController {

    @Autowired
    private PermissionService functionService;

    @GetMapping("load")
    public PageDTO<PermissionDTO> Load(SearchFunctionModel search) {
        return functionService.getFunctionPageListBy(search);
    }

    @PostMapping("search")
    public PageDTO<PermissionDTO> Search(SearchFunctionModel search) {
        return functionService.getFunctionPageListBy(search);
    }

    @PostMapping("add")
    public PermissionDTO Add(PermissionDTO dto) {
        return functionService.insertFunction(dto);
    }

    @PostMapping("edit")
    public PermissionDTO Edit(PermissionDTO dto) {
        return functionService.editFunction(dto);
    }
}
