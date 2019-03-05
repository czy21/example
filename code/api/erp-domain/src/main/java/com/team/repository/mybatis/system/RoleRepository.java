package com.team.repository.mybatis.system;

import com.team.entity.mybatis.system.Role;
import com.team.repository.mybatis.MybatisBaseRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description Role 数据访问层
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleRepository extends MybatisBaseRepository<Role> {

    List<Role> getRolesByLoginName(@Param("loginName") String loginName);

}
