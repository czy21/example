package com.team.controller;


import com.alibaba.fastjson.JSONObject;
import com.team.core.aop.AnnotationLog;
import com.team.core.exception.ErrorCode;
import com.team.core.exception.WebException;
import com.team.core.mvc.Pocket;
import com.team.core.universal.PageModel;
import com.team.core.util.JwtUtil;
import com.team.core.util.TreeUtil;
import com.team.entity.map.MenuMap;
import com.team.entity.map.UserMap;
import com.team.entity.po.Department;
import com.team.entity.po.Menu;
import com.team.entity.po.Role;
import com.team.entity.po.User;
import com.team.entity.vo.LoginDto;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.TokenDto;
import com.team.entity.vo.UserDto;
import com.team.service.RoleMenuService;
import com.team.service.UserRoleService;
import com.team.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private UserService userService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserMap userMap;
    @Autowired
    private MenuMap menuMap;

    @AnnotationLog(remark = "查询用户列表")
    @Pocket(entity = {Role.class, Department.class})
    @RequestMapping("load")
    public PageDto<UserDto> Load(int pageIndex, int pageSize) {
        return userMap.toPageDto(userService.SelectPageList(pageIndex, pageSize));
    }

    @PostMapping("search")
    public PageDto<UserDto> Search(int pageIndex, int pageSize) {
        return userMap.toPageDto(userService.SelectPageList(pageIndex, pageSize));
    }

    @PostMapping("add")
    public UserDto Add(UserDto dto) {
        return userService.insertDefaultPwd(dto);
    }

    @PostMapping("edit")
    public UserDto Edit(UserDto dto) {
        return userService.editUser(dto);
    }

    @PostMapping("modified")
    public Boolean Modified(UserDto dto) {
        return userService.modifiedUser(dto);
    }

    @PostMapping("userRoleDetails")
    public List<String> UserRoleDetails(String userId) {
        return userRoleService.getRolesByUserId(userId);
    }

    @PostMapping(value = "updateUserRole")
    public String updateUserRole(String userId, @RequestParam(value = "userRoleIds[]", required = false) String[] userRoleIds) {
        return userRoleService.insertOrUpdateUserRole(userId, userRoleIds);
    }



    @PostMapping("/login")
    public JSONObject Login(String loginName, String password) {
        User user = userService.SelectBy("LoginName", loginName);
        if (user == null) {
            throw new WebException(ErrorCode.NO_USER, "用户不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new WebException(ErrorCode.PASSWORD_ERROR, "密码错误");
        }
        JSONObject json = new JSONObject();
        TokenDto token = new TokenDto();

        token.setUser(userMap.toLoginDto(user));
        token.setMenus(TreeUtil.createTreeMenus(menuMap.toMenuTree(roleMenuService.getMenusByUserId(user.getUserId()))));
        token.setValue(JwtUtil.GenerateToken(user.getLoginName(), user.getPassword()));
        json.put("token", token);
        return json;
    }
}

