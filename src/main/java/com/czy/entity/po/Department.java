package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Description Department 实体
 * @Author 陈昭宇
 * @Date 2018-09-24
 */
public class Department extends BaseEntity<Department> {

    private Boolean Enabled;
    private String Name;
    private String ParentId;
    private String Phone;
    private String Comment;
    private String CompanyId;


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

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String ParentId) {
        this.ParentId = ParentId;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String CompanyId) {
        this.CompanyId = CompanyId;
    }

    @Override
    public String toString() {
        return "Department{" +
        ", Enabled=" + Enabled +
        ", Name=" + Name +
        ", ParentId=" + ParentId +
        ", Phone=" + Phone +
        ", Comment=" + Comment +
        ", CompanyId=" + CompanyId +
        "}";
    }
}
