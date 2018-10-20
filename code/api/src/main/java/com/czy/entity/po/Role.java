package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 角色表
 *
 * @author 陈昭宇
 * @since 2018-10-20
 */
public class Role extends BaseEntity<Role> {

    @TableId
    private String RoleId;
    private String RoleName;
    private String Comment;
    private Boolean Enabled;


    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String RoleId) {
        this.RoleId = RoleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public Boolean getEnabled() {
        return Enabled;
    }

    public void setEnabled(Boolean Enabled) {
        this.Enabled = Enabled;
    }

    @Override
    public String toString() {
        return "Role{" +
                ", RoleId=" + RoleId +
                ", RoleName=" + RoleName +
                ", Comment=" + Comment +
                ", Enabled=" + Enabled +
                "}";
    }
}
