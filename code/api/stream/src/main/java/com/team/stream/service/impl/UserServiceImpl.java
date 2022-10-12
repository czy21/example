package com.team.stream.service.impl;

import com.czy.learning.db.datasource.DynamicDataSourceContext;
import com.team.stream.mapper.UserMapper;
import com.team.stream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.stream.IntStream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void batchAdd(String tableName, Integer size) {
        ExecutorService executor = Executors.newFixedThreadPool(500);
        IntStream.range(0, size).forEach(t ->
                executor.execute(() -> {
                    DynamicDataSourceContext.put("ds2");
                    userMapper.insert(tableName, Integer.toString(t));
                }));
    }
}
