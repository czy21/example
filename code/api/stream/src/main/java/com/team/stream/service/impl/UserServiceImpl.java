package com.team.stream.service.impl;

import com.czy.learning.db.datasource.DynamicDataSourceContext;
import com.team.stream.mapper.UserMapper;
import com.team.stream.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    ExecutorService executor = Executors.newFixedThreadPool(500);

    @Override
    public void syncInsert(String dataSource, String tableName, Integer size) {
        DynamicDataSourceContext.put(dataSource);
        IntStream.rangeClosed(1, size).forEach(t -> userMapper.insert(tableName, Integer.toString(t)));
    }

    @Override
    public void parallelInsert(String dataSource, String tableName, Integer size, Integer batchSize) {
        List<List<Integer>> sizeGroup = ListUtils.partition(IntStream.rangeClosed(1, size).boxed().collect(Collectors.toList()), batchSize)
                .stream()
                .map(t -> IntStream.rangeClosed(1, t.size()).boxed().collect(Collectors.toList())).collect(Collectors.toList());
        for (int i = 1; i <= sizeGroup.size(); i++) {
            List<Integer> t = sizeGroup.get(i - 1);
            List<CompletableFuture<Void>> futures = t.stream()
                    .map(s -> CompletableFuture.runAsync(
                            () -> {
                                DynamicDataSourceContext.put(dataSource);
                                userMapper.insert(tableName, Integer.toString(s));
                            }, executor)).collect(Collectors.toList());
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[]{})).join();
            log.info("parallelInsert: {} {} {} {} finished", dataSource, size, t.size(), i);
        }
    }
}
