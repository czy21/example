package com.team.controller.system;


import com.team.core.configure.Pocket;
import com.team.entity.dto.PageDto;
import com.team.entity.dto.RoleDto;
import com.team.entity.map.RoleMap;
import com.team.entity.system.Menu;
import com.team.extension.entity.MenuExtension;
import com.team.service.RoleMenuService;
import com.team.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description Role 前端控制器
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@RestController
@RequestMapping("api/role")
@Api(tags = "Role", description = "角色操作接口")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleMap roleMap;

    @GetMapping("load")
    @ApiOperation(value = "加载角色列表")
    @Pocket(entity = {Menu.class})
    public PageDto<RoleDto> Load(int pageIndex, int pageSize) {
        return roleMap.toPageDto(roleService.SelectPageListBy(pageIndex, pageSize, null));
    }

    @PostMapping("search")
    @ApiOperation(value = "查询角色列表")
    public PageDto<RoleDto> Search(int pageIndex, int pageSize) {
        return roleMap.toPageDto(roleService.SelectPageListBy(pageIndex, pageSize, null));
    }

    @PostMapping("add")
    @ApiOperation(value = "添加角色信息")
    public RoleDto Add(RoleDto dto) {
        return roleService.insertRole(dto);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改角色信息")
    public RoleDto Edit(RoleDto dto) {
        return roleService.editRole(dto);
    }

    @PostMapping("roleMenuDetails")
    @ApiOperation(value = "查询角色菜单")
    public Map RoleMenuDetails(String roleId) {
        Map<String, Object> hash = new HashMap<>();
        hash.put("menuIds", roleMenuService.getPermissionsByRoleId(roleId));
        hash.put("actions", MenuExtension.transPermissionToRadioGroups());
        return hash;
    }

    @PostMapping("updateRoleMenu")
    @ApiOperation(value = "更新角色菜单")
    public String updateRoleMenu(String roleId, @RequestParam(value = "roleMenuIds[]", required = false) String[] roleMenuIds) {
        return roleMenuService.insertOrUpdateRoleMenu(roleId, roleMenuIds);
    }


}