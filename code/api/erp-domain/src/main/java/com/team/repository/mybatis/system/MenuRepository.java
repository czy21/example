package com.team.repository.mybatis.system;

import com.team.entity.mybatis.system.Menu;
import com.team.repository.mybatis.MybatisBaseRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description Menu 数据访问层
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface MenuRepository extends MybatisBaseRepository<Menu> {

    List<Menu> getMenusByUserId(@Param("userId") Long userId);

    List<Menu> getMenusByRoleId(@Param("roleId") Long roleId);

}
