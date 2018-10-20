package com.czy.controller;


import com.czy.core.mvc.Pocket;
import com.czy.entity.po.Menu;
import com.czy.entity.po.User;
import com.czy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description User 前端控制器
 * @Author 陈昭宇
 * @Date 2018-09-24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("GetUsers")
    @Pocket(entity = {User.class, Menu.class})
    public List<User> GetUsers() {
        return service.SelectList();
    }

    @PostMapping("/login")
    public User Login(String loginName, String password) {
//        Subject currentUser = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
//        //登录
//        try {
//            currentUser.login(token);
//
//        } catch (IncorrectCredentialsException i) {
//            token.clear();
//            throw new ServiceException("密码输入错误");
//        }
//        User user = (User) currentUser.getPrincipal();
        return null;
    }
}

