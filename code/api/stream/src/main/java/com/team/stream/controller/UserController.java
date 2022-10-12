package com.team.stream.controller;

import com.team.stream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user")
public class UserController {



    @Autowired
    UserService userService;

    @GetMapping(path = "batchAdd")
    public Integer batchAdd(@RequestParam("size") Integer size) {
        userService.batchAdd("user_1", size);
        return 1;
    }
}
