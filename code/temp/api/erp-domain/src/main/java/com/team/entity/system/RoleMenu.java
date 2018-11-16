package com.team.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 角色菜单表
 *
 * @author 陈昭宇
 * @since 2018-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenu extends BaseEntity<RoleMenu> {
    @TableId
    private String RoleMenuId;
    private String RoleId;
    private String MenuId;
}
