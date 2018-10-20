package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 角色菜单表
 *
 * @author 陈昭宇
 * @since 2018-10-20
 */
public class RoleMenu extends BaseEntity<RoleMenu> {

    @TableId
    private String RoleMenuId;
    private String RoleId;
    private String MenuId;


    public String getRoleMenuId() {
        return RoleMenuId;
    }

    public void setRoleMenuId(String RoleMenuId) {
        this.RoleMenuId = RoleMenuId;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String RoleId) {
        this.RoleId = RoleId;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String MenuId) {
        this.MenuId = MenuId;
    }

    @Override
    public String toString() {
        return "RoleMenu{" +
                ", RoleMenuId=" + RoleMenuId +
                ", RoleId=" + RoleId +
                ", MenuId=" + MenuId +
                "}";
    }
}
