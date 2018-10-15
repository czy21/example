package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @Description UserRole 实体
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public class UserRole extends BaseEntity<UserRole> {

    private String UserId;
    private String RoleId;


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
        ", UserId=" + UserId +
        ", RoleId=" + RoleId +
        "}";
    }
}
