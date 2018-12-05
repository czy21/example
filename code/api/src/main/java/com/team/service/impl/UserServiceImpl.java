package com.team.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.team.core.exception.ErrorCode;
import com.team.core.exception.WebException;
import com.team.core.extension.StringExtension;
import com.team.core.universal.BaseServiceImpl;
import com.team.core.util.JwtUtil;
import com.team.core.util.TreeUtil;
import com.team.entity.map.MenuMap;
import com.team.entity.map.UserMap;
import com.team.entity.po.User;
import com.team.entity.vo.LoginDto;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.TokenDto;
import com.team.entity.vo.UserDto;
import com.team.model.SearchUserModel;
import com.team.service.RoleMenuService;
import com.team.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @Description User 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Resource
    private UserMap userMap;

    @Resource
    private RoleMenuService roleMenuService;

    @Resource
    private MenuMap menuMap;

    @Override
    public UserDto insertDefaultPwd(UserDto dto) {
        if (StringUtils.isEmpty(dto.getUserName())) {
            throw new WebException(ErrorCode.NAME_EXIST, "用户姓名已存在");
        }
        if (super.SelectBy("LoginName", dto.getLoginName()) != null) {
            throw new WebException(ErrorCode.ACCOUNT_EXIST, "用户账号已存在");
        }
        User user = userMap.toUser(dto);
        user.setPassword("123456");
        return userMap.toUserDto(super.InsertAndGetEntity(user));
    }

    @Override
    public UserDto editUser(UserDto dto) {
        if (StringUtils.isEmpty(dto.getUserId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "用户Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getUserName())) {
            throw new WebException(ErrorCode.NAME_NO_NULL, "用户姓名不能为空");
        }
        return userMap.toUserDto(super.UpdateAndGetEntity(userMap.toUser(dto)));
    }

    @Override
    public Boolean modifiedUser(UserDto dto) {
        if (StringUtils.isEmpty(dto.getUserId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "用户Id不能为空");
        }
        return super.UpdateAndGetEntity(userMap.toUser(dto)).getEnabled();
    }

    @Override
    public PageDto<UserDto> getUserPageListBy(SearchUserModel search) {
        return userMap.toPageDto(super.SelectPageListBy(search.getPageIndex(), search.getPageSize(), null));
    }

    @Override
    public JSONObject Login(LoginDto dto) {
        User user = super.SelectBy("LoginName", dto.getLoginName());
        if (user == null) {
            throw new WebException(ErrorCode.NO_USER, "用户不存在");
        }
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new WebException(ErrorCode.PASSWORD_ERROR, "密码错误");
        }
        JSONObject json = new JSONObject();
        TokenDto token = new TokenDto();
        token.setUser(userMap.toAccountDto(user));
        token.setMenus(TreeUtil.createTreeMenus(menuMap.toMenuTree(roleMenuService.getMenusByUserId(user.getUserId()))));
        token.setValue(JwtUtil.GenerateToken(user.getLoginName(), user.getPassword()));
        json.put("token", token);
        return json;
    }
}
