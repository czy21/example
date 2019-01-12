package com.team.repository.mybatis.system;

import com.team.repository.mybatis.MybatisBaseRepository;
import com.team.entity.mybatis.system.Menu;
import com.team.entity.mybatis.system.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description RoleMenu 数据访问层
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleMenuRepositoryMybatis extends MybatisBaseRepository<RoleMenu> {

    List<Menu> getMenusByUserId(@Param("userId") String userId, @Param("isMenu") Boolean isMenu);

    List<Menu> getMenusByRoleId(@Param("roleId") String roleId, @Param("isMenu") Boolean isMenu);
}
