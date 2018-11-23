package com.team.service;

import com.alibaba.fastjson.JSONObject;
import com.team.entity.po.User;
import com.team.core.universal.BaseService;
import com.team.entity.vo.LoginDto;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.UserDto;
import com.team.model.SearchUserModel;

/**
 * @Description User 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserService extends BaseService<User> {

    UserDto insertDefaultPwd(UserDto user);

    UserDto editUser(UserDto dto);

    Boolean modifiedUser(UserDto dto);

    PageDto<UserDto> getUserPageListBy(SearchUserModel search);

    JSONObject Login(LoginDto dto);

}
