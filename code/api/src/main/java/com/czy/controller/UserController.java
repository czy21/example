package com.czy.controller;


import com.alibaba.fastjson.JSONObject;
import com.czy.core.exception.ErrorCode;
import com.czy.core.exception.WebException;
import com.czy.core.util.JwtUtil;
import com.czy.core.mvc.Pocket;
import com.czy.entity.map.UserMap;
import com.czy.entity.po.Menu;
import com.czy.entity.po.User;
import com.czy.entity.vo.UserDto;
import com.czy.service.UserRoleService;
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
    private UserRoleService service;
    @Autowired
    private UserService userService;

    @Autowired
    private UserMap userMap;

    @RequestMapping("GetUsers")
    @Pocket(entity = {User.class, Menu.class})
    public List<UserDto> GetUsers() {
        return userMap.toUserDtos(userService.SelectList());
    }

    @PostMapping("/login")
    public Object Login(String loginName, String password) {
        JSONObject json = new JSONObject();
        User user = userService.SelectBy("LoginName", loginName);
        if (user == null) {
            throw new WebException(ErrorCode.NO_USER, "用户不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new WebException(ErrorCode.PASSWORD_ERROR, "密码错误");
        }
        json.put("token", JwtUtil.GenerateToken(user.getLoginName(), user.getPassword()));
        json.put("userInfo", userMap.toLoginDto(user));
        return json;
    }
}

