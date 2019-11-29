package com.team.portal.controller;


import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.vo.SearchUserModel;
import com.team.application.service.UserRoleService;
import com.team.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public UserDTO add(@RequestBody UserDTO dto) {
        return userService.insertDefaultPwd(dto);
    }

    @PostMapping("edit")
    public UserDTO edit(@RequestBody UserDTO dto) {
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
}

