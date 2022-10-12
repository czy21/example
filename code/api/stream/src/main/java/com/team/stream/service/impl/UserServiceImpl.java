package com.team.stream.service.impl;

import com.czy.learning.db.annotation.DS;
import com.team.stream.mapper.UserMapper;
import com.team.stream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @DS("ds2")
    @Override
    public void batchAdd(String tableName, Integer size) {
        IntStream.range(0, size).forEach(t -> userMapper.insert(tableName, Integer.toString(t)));
    }
}
