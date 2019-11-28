package com.team.domain.repository;

import com.team.domain.infrastructure.base.MongoBaseRepository;
import com.team.domain.entity.LogEntity;

/**
 * @author 陈昭宇
 * @description Log 数据访问层
 * @since 2018-10-28
 */
public interface LogRepository extends MongoBaseRepository<LogEntity, String> {

}
