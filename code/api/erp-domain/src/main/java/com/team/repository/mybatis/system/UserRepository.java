package com.team.repository.mybatis.system;

import com.team.entity.mybatis.system.Function;
import com.team.entity.mybatis.system.Role;
import com.team.entity.mybatis.system.User;
import com.team.repository.mybatis.MybatisBaseRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description User 数据访问层
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserRepository extends MybatisBaseRepository<User> {
    List<Role> getRolesByLoginName(@Param("loginName") String loginName);

    List<Function> getFunctionsByRole(@Param("roleIds") List<Long> roleIds);
}
