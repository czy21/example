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
//        UserPO aQuery = new UserPO();
//        aQuery.setPhoneNo("15145033859");
//        UserPO a = userMapper.selectOneBy(aQuery);

//        UserPO bEntity=new UserPO();
//        bEntity.setId(1L);
//        bEntity.setPhoneNo("15145033859");
//        int b=userMapper.updateBy(bEntity);
        UserPO cEntity = new UserPO();
        cEntity.setName("haha");
        cEntity.setPhoneNo("15145033859");
        cEntity.setIdNum("2301831111111111");
        userMapper.insertOne(cEntity);
//        UserPO b = userMapper.selectOneById(9999L);
//        int count = userMapper.count();
        return userMapper.selectListBy(new UserPO());
    }
}
