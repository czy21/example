package com.team.repository;

import com.team.entity.LogEntity;
import com.team.infrastructure.MongoBaseRepository;

/**
 * @author 陈昭宇
 * @description Log 数据访问层
 * @since 2018-10-28
 */
public interface LogRepository extends MongoBaseRepository<LogEntity, String> {

}
