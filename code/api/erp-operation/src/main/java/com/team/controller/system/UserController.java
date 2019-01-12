package com.team.controller.system;


import com.alibaba.fastjson.JSONObject;
import com.team.core.annotations.Pocket;
import com.team.entity.dto.LoginDto;
import com.team.entity.dto.PageDto;
import com.team.entity.dto.UserDto;
import com.team.entity.mybatis.system.Department;
import com.team.entity.mybatis.system.Role;
import com.team.model.SearchUserModel;
import com.team.service.UserRoleService;
import com.team.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


/**
 * @author 陈昭宇
 * @description User 前端控制器
 * @date 2018-09-24
 */
@RestController
@RequestMapping("api/user")
@Api(tags = "User", description = "用户操作接口")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("load")
    @Pocket(entity = {Role.class, Department.class})
    @ApiOperation(value = "加载用户列表")
    public PageDto<UserDto> Load(SearchUserModel search) {
        return userService.getUserPageListBy(search);
    }

    @PostMapping("search")
    @ApiOperation(value = "查询用户列表")
    public PageDto<UserDto> Search(SearchUserModel search) {
        return userService.getUserPageListBy(search);
    }

    @PostMapping("add")
    @ApiOperation(value = "添加用户信息")
    public UserDto Add(UserDto dto) {
        return userService.insertDefaultPwd(dto);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改用户信息")
    public UserDto Edit(UserDto dto) {
        return userService.editUser(dto);
    }

    @PostMapping("modified")
    @ApiOperation(value = "更改用户状态")
    public Boolean Modified(UserDto dto) {
        return userService.modifiedUser(dto);
    }

    @PostMapping("userRoleDetails")
    @ApiOperation(value = "获取用户角色")
    public List<String> UserRoleDetails(String userId) {
        return userRoleService.getRolesByUserId(userId);
    }

    @PostMapping(value = "updateUserRole")
    @ApiOperation(value = "更新用户角色")
    public String updateUserRole(String userId, @RequestParam(value = "userRoleIds[]", required = false) String[] userRoleIds) {
        return userRoleService.insertOrUpdateUserRole(userId, userRoleIds);
    }

    @ApiIgnore
    @PostMapping("login")
    public JSONObject Login(LoginDto dto) {
        return userService.login(dto);
    }
}

