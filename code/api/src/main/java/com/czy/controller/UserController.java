package com.czy.controller;


import com.czy.core.mvc.Pocket;
import com.czy.dao.RoleMenuDao;
import com.czy.dao.UserDao;
import com.czy.dao.UserRoleDao;
import com.czy.entity.po.Menu;
import com.czy.entity.po.Role;
import com.czy.entity.po.User;
import com.czy.entity.po.UserRole;
import com.czy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RoleMenuDao dao;

    @RequestMapping("GetUsers")
    @Pocket(entity = {User.class, Menu.class})
    public List<String> GetUsers() {
        return dao.getPermissionsByUserId("1e6b2317-cee7-11e8-8984-1cb72c963248");
    }


}

