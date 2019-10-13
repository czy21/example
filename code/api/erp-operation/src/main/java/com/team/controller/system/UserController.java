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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author 陈昭宇
 * @description User 前端控制器
 * @date 2018-09-24
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("load")
    @Pocket(entity = Role.class)
    @Pocket(entity = Department.class)

    //@PreAuthorize("hasAuthority('SearchUser')")
    public PageDto<UserDto> Load(SearchUserModel search) {
        return userService.getUserPageListBy(search);
    }

    @PostMapping("search")

    //@PreAuthorize("hasAuthority('SearchUser')")
    public PageDto<UserDto> Search(SearchUserModel search) {
        return userService.getUserPageListBy(search);
    }

    @PostMapping("add")

    //@PreAuthorize("hasAuthority('AddUser')")
    public UserDto Add(UserDto dto) {
        return userService.insertDefaultPwd(dto);
    }

    @PostMapping("edit")

    //@PreAuthorize("hasAuthority('EditUser')")
    public UserDto Edit(UserDto dto) {
        return userService.editUser(dto);
    }

    @PostMapping("modified")

    //@PreAuthorize("hasAuthority('DisableUser')")
    public Boolean Modified(UserDto dto) {
        return userService.modifiedUser(dto);
    }

    @PostMapping("userRoleDetails")

    //@PreAuthorize("hasAuthority('AllotUserRole')")
    public List<Long> UserRoleDetails(Long userId) {
        return userRoleService.getRolesByUserId(userId);
    }

    @PostMapping(value = "updateUserRole")

    //@PreAuthorize("hasAuthority('AllotUserRole')")
    public String updateUserRole(Long userId, @RequestParam(value = "userRoleIds[]", required = false) Long[] userRoleIds) {
        return userRoleService.insertOrUpdateUserRole(userId, userRoleIds);
    }

    @PostMapping("login")
    public JSONObject Login(LoginDto dto) {
        return userService.login(dto);
    }


    @PostMapping("register")
    public JSONObject register(LoginDto dto) {
        return userService.register(dto);
    }
}

