package com.team.service;

import com.team.entity.po.User;
import com.team.core.universal.BaseService;
import com.team.entity.vo.UserDto;

/**
 * @Description User 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserService extends BaseService<User> {

    User InsertDefaultPwd(User user);

    User EditUser(UserDto dto);

    User ModifiedUser(UserDto dto);

}
