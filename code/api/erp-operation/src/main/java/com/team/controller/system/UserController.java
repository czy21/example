package com.team.controller.system;


import com.alibaba.fastjson.JSONObject;
import com.team.entity.dto.LoginDTO;
import com.team.entity.dto.PageDTO;
import com.team.entity.dto.UserDTO;
import com.team.model.SearchUserModel;
import com.team.service.UserRoleService;
import com.team.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PageDTO<UserDTO> load(SearchUserModel search) {
        return userService.getUserPageListBy(search);
    }

    @PostMapping("search")
    public PageDTO<UserDTO> search(SearchUserModel search) {
        return userService.getUserPageListBy(search);
    }

    @PostMapping("add")
    public UserDTO add(UserDTO dto) {
        return userService.insertDefaultPwd(dto);
    }

    @PostMapping("edit")
    public UserDTO edit(UserDTO dto) {
        return userService.editUser(dto);
    }

    @PostMapping("modified")
    public Boolean modified(UserDTO dto) {
        return userService.modifiedUser(dto);
    }

    @PostMapping("userRoleDetails")
    public List<String> userRoleDetails(String userId) {
        return userRoleService.getRolesByUserId(userId);
    }

    @PostMapping("login")
    public JSONObject login(LoginDTO dto) {
        return userService.login(dto);
    }


    @PostMapping("register")
    public JSONObject register(LoginDTO dto) {
        return userService.register(dto);
    }
}

