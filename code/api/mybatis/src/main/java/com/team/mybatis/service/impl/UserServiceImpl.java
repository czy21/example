package com.team.mybatis.service.impl;

import com.team.mybatis.entity.UserPO;
import com.team.mybatis.mapper.UserMapper;
import com.team.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserPO> findAll() {
        UserPO a = userMapper.selectOneBy(new UserPO());
        UserPO b = userMapper.selectOneById(9999L);
        int count = userMapper.count();
        return userMapper.selectListBy(new UserPO());
    }
}
