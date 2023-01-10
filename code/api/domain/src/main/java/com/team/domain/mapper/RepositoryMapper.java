package com.team.domain.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.mapping.ResultSetType;

import java.util.List;
import java.util.Map;

public interface RepositoryMapper {
    @Select(value = "${sql}")
    @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = Integer.MIN_VALUE)
    List<Map<String, Object>> selectDynamicSql(Map<String, Object> parameters);

    @Update("${sql}")
    void insert(Map<String, Object> param);

    @Select(value = "${sql}")
    List<Map<String, Object>> select();

    @Options(fetchSize = Integer.MIN_VALUE, resultSetType = ResultSetType.FORWARD_ONLY)
    @Select(value = "select * from kafka_topic")
    Cursor<Map<String, Object>> selectAllTopic();

    @Select(value = "select * from kafka_topic")
    List<Map<String, Object>> selectAll();
}
