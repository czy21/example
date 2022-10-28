package com.team.mybatis.mapper;

import com.team.mybatis.entity.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Insert(value = "insert into ${tableName}(name) values (#{name})")
    int insert(@Param("tableName") String tableName, @Param("name") String name);

    @Select(value = "select * from ent_sys_safe_user")
    List<UserPO> selectListBy(UserPO query);

    @Select(value = "select * from ent_sys_safe_user limit 1")
    UserPO selectOneBy(UserPO query);

    @Select(value = "select count(0) from ent_sys_safe_user")
    int count();

    @Select(value = "select * from ent_sys_safe_user where id = #{id}")
    UserPO selectOneById(@Param("id") Long id);
}
