package com.team.orm.controller;


import com.team.orm.entity.UserPO;
import com.team.orm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping(path = "search")
    public List<UserPO> search() {
        return userService.findAll();
    }
}
