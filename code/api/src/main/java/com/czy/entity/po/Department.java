package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 部门表
 *
 * @author 陈昭宇
 * @since 2018-10-20
 */
public class Department extends BaseEntity<Department> {

    @TableId
    private String DepartmentId;
    private String ParentId;
    private String DepartmentName;
    private String Phone;
    private String Comment;
    private String CompanyId;
    private Boolean Enabled;


    public String getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(String DepartmentId) {
        this.DepartmentId = DepartmentId;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String ParentId) {
        this.ParentId = ParentId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
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

    public Boolean getEnabled() {
        return Enabled;
    }

    public void setEnabled(Boolean Enabled) {
        this.Enabled = Enabled;
    }

    @Override
    public String toString() {
        return "Department{" +
                ", DepartmentId=" + DepartmentId +
                ", ParentId=" + ParentId +
                ", DepartmentName=" + DepartmentName +
                ", Phone=" + Phone +
                ", Comment=" + Comment +
                ", CompanyId=" + CompanyId +
                ", Enabled=" + Enabled +
                "}";
    }
}
