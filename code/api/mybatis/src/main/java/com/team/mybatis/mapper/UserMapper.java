package com.team.mybatis.mapper;

import com.meditrusthealth.mth.common.security.annotation.SensitiveAction;
import com.meditrusthealth.mth.common.security.annotation.SensitiveEnum;
import com.team.mybatis.entity.UserPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Insert(value = "insert into ${tableName}(name) values (#{name})")
    int insert(@Param("tableName") String tableName, @Param("name") String name);

    @Options
    @SensitiveAction(selectParam = SensitiveEnum.ENCRYPT)
    @Select(value = "select * from ent_sys_safe_user")
    List<UserPO> selectListBy(UserPO query);

    @Select(value = "select * from ent_sys_safe_user where phone_no = #{phoneNo} limit 1")
    UserPO selectOneBy(UserPO query);

    @Select(value = "select * from ent_sys_safe_user where phone_no = #{phoneNo} and name =#{name} limit 1")
    UserPO selectOneByPhoneNo(@Param("phoneNo") String phoneNo, @Param("name") String name);

    @Select(value = "select count(0) from ent_sys_safe_user")
    int count();

    @Select(value = "select * from ent_sys_safe_user where id = #{id}")
    UserPO selectOneById(@Param("id") Long id);

    @Update(value = "update ent_sys_safe_user set phone_no = #{phoneNo} where id = #{id}")
    int updateBy(UserPO entity);

    @Insert(value = "insert into ent_sys_safe_user(name,phone_no,id_num)values (#{name},#{phoneNo},#{idNum})")
    int insertOne(UserPO entity);

    int batchInsert(@Param("items") List<UserPO> items);
}
