package com.team.repository.mybatis.system;

import com.team.entity.mybatis.system.Function;
import com.team.repository.mybatis.MybatisBaseRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 陈昭宇
 * @description Function 数据访问层
 * @date 2019-03-03
 */
public interface FunctionRepository extends MybatisBaseRepository<Function> {

    List<Function> getFunctionsByRoleIds(@Param("roleIds") List<Long> roleIds);

    List<Function> getFunctionsByUserId(@Param("userId") Long userId);
}
