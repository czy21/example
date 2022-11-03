package com.team.orm.mapper;

import com.team.orm.entity.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Insert(value = "insert into ${tableName}(name) values (#{name})")
    int insert(@Param("tableName") String tableName, @Param("name") String name);

    @Select(value = "select * from  basic_user")
    List<UserPO> selectListBy(UserPO query);

    @Select(value = "select * from  basic_user where phone_no = #{phoneNo} limit 1")
    UserPO selectOneBy(UserPO query);

    @Select(value = "select * from  basic_user where phone_no = #{phoneNo} and name =#{name} limit 1")
    UserPO selectOneByPhoneNo(@Param("phoneNo") String phoneNo, @Param("name") String name);

    @Select(value = "select count(0) from  basic_user")
    int count();

    @Select(value = "select * from  basic_user where id = #{id}")
    UserPO selectOneById(@Param("id") Long id);

    @Update(value = "update  basic_user set phone_no = #{phoneNo} where id = #{id}")
    int updateBy(UserPO entity);

    @Insert(value = "insert into  basic_user(name,phone_no,id_num)values (#{name},#{phoneNo},#{idNum})")
    int insertOne(UserPO entity);

    int batchInsert(@Param("items") List<UserPO> items);
}
