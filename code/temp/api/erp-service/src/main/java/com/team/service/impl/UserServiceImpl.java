package com.team.service.impl;

import com.team.entity.system.User;
import com.team.exception.ErrorCode;
import com.team.exception.WebException;
import com.team.service.UserService;
import com.team.universal.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description User 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {


    @Override
    public User InsertDefaultPwd(User user) {
        if (super.SelectBy("LoginName", user.getLoginName()) != null) {
            throw new WebException(ErrorCode.NAME_EXIST, "账号已存在");
        }
        user.setPassword("123456");
        return super.InsertAndGetEntity(user);
    }
}
