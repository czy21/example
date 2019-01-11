package com.team.dao.mongo;

import com.team.entity.system.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

/**
 * @author 陈昭宇
 * @description Log 数据访问层
 * @since 2018-10-28
 */
public interface LogDao extends MongoRepository<Log, String> {

//    Integer insertByBatch(List<Log> list);

//    List<Log> selectLogList(@Param("column") String column, @Param("sort") String sort);
}
