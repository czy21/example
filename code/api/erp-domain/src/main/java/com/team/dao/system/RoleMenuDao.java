package com.team.dao.system;

import com.team.dao.BaseDao;
import com.team.entity.system.Menu;
import com.team.entity.system.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description RoleMenu 数据访问层
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface RoleMenuDao extends BaseDao<RoleMenu> {

    List<Menu> getMenusByUserId(@Param("userId") String userId, @Param("isMenu") Boolean isMenu);

    List<Menu> getMenusByRoleId(@Param("roleId") String roleId, @Param("isMenu") Boolean isMenu);
}
