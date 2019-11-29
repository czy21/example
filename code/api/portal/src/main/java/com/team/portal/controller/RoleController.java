package com.team.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.domain.entity.RoleEntity;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.RoleDTO;
import com.team.application.model.automap.RoleAutoMap;
import com.team.application.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description Role 前端控制器
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleAutoMap roleMap;

    @GetMapping("load")
    public PageDTO<RoleDTO> load(int pageIndex, int pageSize) {
        return roleMap.mapToPageDto(roleService.selectAll(pageIndex, pageSize, (QueryWrapper<RoleEntity>) null));
    }

    @PostMapping("search")
    public PageDTO<RoleDTO> search(int pageIndex, int pageSize) {
        return roleMap.mapToPageDto(roleService.selectAll(pageIndex, pageSize, (QueryWrapper<RoleEntity>) null));
    }

    @PostMapping("add")
    public RoleDTO add(RoleDTO dto) {
        return roleService.insertRole(dto);
    }

    @PostMapping("edit")
    public RoleDTO edit(RoleDTO dto) {
        return roleService.editRole(dto);
    }

    @PostMapping("roleFuncDetails")
    public List<String> roleFuncDetails(String roleId) {
        return roleService.getFunctionsByRoleId(roleId);
    }

}