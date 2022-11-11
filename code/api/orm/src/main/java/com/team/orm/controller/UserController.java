package com.team.orm.controller;


import com.team.orm.entity.UserPO;
import com.team.orm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "user")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping(path = "search")
    public List<UserPO> search() {
        return userService.findAll();
    }

    @PostMapping(path = "cache/put")
    public void cachePut() {
        userService.put(UUID.randomUUID().toString(), new byte[2 * 1024 * 1024]);
    }
}
