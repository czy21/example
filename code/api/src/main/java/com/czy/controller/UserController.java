package com.czy.controller;


import com.alibaba.fastjson.JSONObject;
import com.czy.core.jwt.JwtUtil;
import com.czy.core.mvc.Pocket;
import com.czy.entity.po.Menu;
import com.czy.entity.po.User;
import com.czy.service.UserRoleService;
import com.czy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description User 前端控制器
 * @Author 陈昭宇
 * @Date 2018-09-24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRoleService service;
    @Autowired
    private UserService userService;

    @RequestMapping("GetUsers")
    @Pocket(entity = {User.class, Menu.class})
    public Object GetUsers() {
        return userService.SelectBy("LoginName", "admin");

    }

    @PostMapping("/login")
    public Object Login(String loginName, String password) {

        JSONObject json = new JSONObject();
        User user = userService.SelectBy("LoginName", loginName);
        if (user == null) {
            json.put("message", "登录失败,用户不存在");
            return json;
        } else {
            if (!user.getPassword().equals(password)) {
                json.put("message", "登录失败,密码错误");
                return json;
            } else {
                String token = JwtUtil.GenerateToken(user.getLoginName(), user.getPassword());
                json.put("token", token);
                json.put("user", user);
                return json;
            }
        }
    }
}

