package com.team.mybatis.controller;


import com.team.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping(path = "search")
    public Integer syncInsert() {
        return 1;
    }
}
