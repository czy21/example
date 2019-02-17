package com.team.entity.mybatis.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 陈昭宇
 * @description 角色菜单表
 * @date 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenu extends BaseEntity<RoleMenu> {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long roleMenuId;
    private Long roleId;
    private Long menuId;
}
