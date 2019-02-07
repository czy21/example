package com.team.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.entity.dto.LoginDto;
import com.team.entity.dto.PageDto;
import com.team.entity.dto.TokenDto;
import com.team.entity.dto.UserDto;
import com.team.entity.map.MenuMap;
import com.team.entity.map.UserMap;
import com.team.entity.mybatis.system.User;
import com.team.exception.ErrorCode;
import com.team.exception.WebException;
import com.team.extension.MenuExtension;
import com.team.model.SearchUserModel;
import com.team.service.RoleMenuService;
import com.team.service.UserService;
import com.team.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @Description User 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class UserServiceImplMybatis extends MybatisBaseServiceImpl<User> implements UserService {

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
        if (super.SelectBy(User::getLoginName, dto.getLoginName()) != null) {
            throw new WebException(ErrorCode.ACCOUNT_EXIST, "用户账号已存在");
        }
        User user = userMap.mapToEntity(dto);
        user.setPassword("123456");
        return userMap.mapToDto(super.InsertAndGetEntity(user));
    }

    @Override
    public UserDto editUser(UserDto dto) {
        if (StringUtils.isEmpty(dto.getUserId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "用户Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getUserName())) {
            throw new WebException(ErrorCode.NAME_NO_NULL, "用户姓名不能为空");
        }
        return userMap.mapToDto(super.UpdateAndGetEntity(userMap.mapToEntity(dto)));
    }

    @Override
    public Boolean modifiedUser(UserDto dto) {
        if (StringUtils.isEmpty(dto.getUserId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "用户Id不能为空");
        }
        return super.UpdateAndGetEntity(userMap.mapToEntity(dto)).getEnabled();
    }

    @Override
    public PageDto<UserDto> getUserPageListBy(SearchUserModel search) {
        return userMap.mapToPageDto(super.SelectPageListBy(search.getPageIndex(), search.getPageSize(), null));
    }

    @Override
    public JSONObject login(LoginDto dto) {
        User user = super.SelectBy(User::getLoginName, dto.getLoginName());
        if (ObjectUtils.isEmpty(user)) {
            throw new WebException(ErrorCode.NO_USER, "用户不存在");
        }
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new WebException(ErrorCode.PASSWORD_ERROR, "密码错误");
        }
        JSONObject json = new JSONObject();
        TokenDto token = new TokenDto();
        token.setUser(userMap.mapToAccountDto(user));
        token.setMenus(MenuExtension.createTreeMenus(menuMap.toMenuTree(roleMenuService.getMenusByUserId(user.getUserId()))));
        token.setPermissions(roleMenuService.getPermissionOfValuesByUserId(user.getUserId()));
        token.setValue(JwtUtil.GenerateToken(user.getLoginName(), user.getPassword()));
        json.put("token", token);
        return json;
    }
}
