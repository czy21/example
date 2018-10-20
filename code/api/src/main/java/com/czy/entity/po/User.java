package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import javax.persistence.Transient;
import java.util.Set;

/**
 * 用户表
 *
 * @author 陈昭宇
 * @since 2018-10-20
 */
public class User extends BaseEntity<User> {

    @TableId
    private String UserId;
    private String LoginName;
    private String Password;
    private String UserName;
    private String Email;
    private String Phone;
    private Boolean IsHeader;
    private String DepartmentId;
    private Boolean Enabled;

    private String Salt;

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String salt) {
        Salt = salt;
    }

    @Transient
    @TableField(exist = false)
    private Set<String> roles;
    @Transient
    @TableField(exist = false)
    private Set<String> permissions;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String LoginName) {
        this.LoginName = LoginName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public Boolean getIsHeader() {
        return IsHeader;
    }

    public void setIsHeader(Boolean IsHeader) {
        this.IsHeader = IsHeader;
    }

    public String getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(String DepartmentId) {
        this.DepartmentId = DepartmentId;
    }

    public Boolean getEnabled() {
        return Enabled;
    }

    public void setEnabled(Boolean Enabled) {
        this.Enabled = Enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                ", UserId=" + UserId +
                ", LoginName=" + LoginName +
                ", Password=" + Password +
                ", UserName=" + UserName +
                ", Email=" + Email +
                ", Phone=" + Phone +
                ", IsHeader=" + IsHeader +
                ", DepartmentId=" + DepartmentId +
                ", Enabled=" + Enabled +
                "}";
    }
}
