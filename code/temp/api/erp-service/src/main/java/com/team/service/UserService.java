package com.team.service;

import com.team.entity.system.User;
import com.team.universal.BaseService;

/**
 * @Description User 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserService extends BaseService<User> {

    User InsertDefaultPwd(User user);

}
