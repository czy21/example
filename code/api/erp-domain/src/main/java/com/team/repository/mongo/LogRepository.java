package com.team.repository.mongo;

import com.team.entity.mongo.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 陈昭宇
 * @description Log 数据访问层
 * @since 2018-10-28
 */
public interface LogRepository extends MongoBaseRepository<Log> {

//    Integer insertByBatch(List<Log> list);

//    List<Log> selectLogList(@Param("column") String column, @Param("sort") String sort);
}
