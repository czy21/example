package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Description UserRole 实体
 * @Author 陈昭宇
 * @Date 2018-09-24
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
