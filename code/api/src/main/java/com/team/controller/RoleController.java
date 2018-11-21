package com.team.controller;


import com.alibaba.fastjson.JSONObject;
import com.team.core.aop.AnnotationLog;
import com.team.core.mvc.Pocket;
import com.team.entity.map.RoleMap;
import com.team.entity.po.Menu;
import com.team.entity.po.RoleMenu;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.RoleDto;
import com.team.entity.vo.UserDto;
import com.team.service.RoleMenuService;
import com.team.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description Role 前端控制器
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleMap roleMap;

    @RequestMapping("load")
    @AnnotationLog(remark = "查询角色列表")
    @Pocket(entity = {Menu.class})
    public PageDto<RoleDto> Load(int pageIndex, int pageSize) {
        return roleMap.toPageDto(roleService.SelectPageList(pageIndex, pageSize));
    }

    @PostMapping("search")
    public PageDto<RoleDto> Search(int pageIndex, int pageSize) {
        return roleMap.toPageDto(roleService.SelectPageList(pageIndex, pageSize));
    }

    @PostMapping("add")
    public RoleDto Add(RoleDto dto) {
        return roleService.insertRole(dto);
    }

    @PostMapping("edit")
    public RoleDto Edit(RoleDto dto) {
        return roleService.editRole(dto);
    }

    @PostMapping("roleMenuDetails")
    public List<String> RoleMenuDetails(String roleId) {
        return roleMenuService.getMenusByRoleId(roleId);
    }

    @PostMapping("updateRoleMenu")
    public String updateRoleMenu(String roleId, @RequestParam(value = "roleMenuIds[]", required = false) String[] roleMenuIds) {
        return roleMenuService.insertOrUpdateRoleMenu(roleId, roleMenuIds);
    }

    @PostMapping("roleActionDetails")
    public List<String> RoleActionDetails(String roleId) {
        return roleMenuService.getPermissionsByRoleId(roleId);
    }

}