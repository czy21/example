package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @Description Role 实体
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public class Role extends BaseEntity<Role> {

    private Boolean Enabled;
    private String Name;
    private String Comment;


    public Boolean getEnabled() {
        return Enabled;
    }

    public void setEnabled(Boolean Enabled) {
        this.Enabled = Enabled;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    @Override
    public String toString() {
        return "Role{" +
        ", Enabled=" + Enabled +
        ", Name=" + Name +
        ", Comment=" + Comment +
        "}";
    }
}
