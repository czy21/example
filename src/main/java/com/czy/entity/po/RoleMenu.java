package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Description RoleMenu 实体
 * @Author 陈昭宇
 * @Date 2018-09-24
 */
public class RoleMenu extends BaseEntity<RoleMenu> {

    private String RoleId;
    private String MenuId;


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
        ", RoleId=" + RoleId +
        ", MenuId=" + MenuId +
        "}";
    }
}
