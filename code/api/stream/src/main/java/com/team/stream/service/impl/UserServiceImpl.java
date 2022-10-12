package com.team.stream.service.impl;

import com.czy.learning.db.datasource.DynamicDataSourceContext;
import com.team.stream.mapper.UserMapper;
import com.team.stream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
            50, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    @Override
    public void batchAdd(String tableName, Integer size) {
        IntStream.range(0, size).forEach(t -> threadPoolExecutor.execute(() -> {
            DynamicDataSourceContext.put("ds2");
            userMapper.insert(tableName, Integer.toString(t));
        }));
    }
}
