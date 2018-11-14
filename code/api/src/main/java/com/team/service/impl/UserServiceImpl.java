package com.team.service.impl;

import com.team.entity.po.User;
import com.team.service.UserService;
import com.team.core.universal.BaseServiceImpl;
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
        user.setPassword("123456");
        return super.InsertAndGetEntity(user);
    }
}
