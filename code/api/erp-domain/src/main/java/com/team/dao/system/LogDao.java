package com.team.dao.system;

import com.baomidou.mybatisplus.extension.api.R;
import com.team.dao.BaseDao;
import com.team.entity.system.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Function;

/**
 * @author 陈昭宇
 * @description Log 数据访问层
 * @since 2018-10-28
 */
public interface LogDao extends BaseDao<Log> {

    Integer insertByBatch(List<Log> list);

    List<Log> selectLogList(@Param("column") String column, @Param("sort") String sort);
}
