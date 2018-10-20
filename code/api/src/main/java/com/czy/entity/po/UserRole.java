package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 用户角色表
 *
 * @author 陈昭宇
 * @since 2018-10-20
 */
public class UserRole extends BaseEntity<UserRole> {

    @TableId
    private String UserRoleId;
    private String UserId;
    private String RoleId;


    public String getUserRoleId() {
        return UserRoleId;
    }

    public void setUserRoleId(String UserRoleId) {
        this.UserRoleId = UserRoleId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String RoleId) {
        this.RoleId = RoleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                ", UserRoleId=" + UserRoleId +
                ", UserId=" + UserId +
                ", RoleId=" + RoleId +
                "}";
    }
}
