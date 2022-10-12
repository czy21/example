package com.team.stream.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    @Insert(value = "insert into ${tableName}(name) values (#{name})")
    int insert(@Param("tableName") String tableName, @Param("name") String name);
}
