package com.team.domain.repository.mongo;

import com.team.domain.entity.LogEntity;
import com.team.infrastructure.base.MongoBaseRepository;

/**
 * @author 陈昭宇
 * @description Log 数据访问层
 * @since 2018-10-28
 */
public interface LogRepository extends MongoBaseRepository<LogEntity, String> {

}
