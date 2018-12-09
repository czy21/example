package com.team.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 角色菜单表
 *
 * @author 陈昭宇
 * @date 2018-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("role_menu_id")
    private String roleMenuId;
    @TableField("role_id")
    private String roleId;
    @TableField("menu_id")
    private String menuId;
}
