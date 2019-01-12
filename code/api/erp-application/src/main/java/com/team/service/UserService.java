package com.team.service;

import com.alibaba.fastjson.JSONObject;
import com.team.core.universal.MybatisBaseService;
import com.team.entity.dto.LoginDto;
import com.team.entity.dto.PageDto;
import com.team.entity.dto.UserDto;
import com.team.entity.mybatis.system.User;
import com.team.model.SearchUserModel;

/**
 * @Description User 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserService extends MybatisBaseService<User> {

    UserDto insertDefaultPwd(UserDto user);

    UserDto editUser(UserDto dto);

    Boolean modifiedUser(UserDto dto);

    PageDto<UserDto> getUserPageListBy(SearchUserModel search);

    JSONObject login(LoginDto dto);

}
