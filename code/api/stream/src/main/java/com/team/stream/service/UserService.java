package com.team.stream.service;

public interface UserService {
    void syncInsert(String dataSource, String tableName, Integer size);

    void parallelInsert(String dataSource, String tableName, Integer size, Integer batchSize);

    void redisSearch();
}
