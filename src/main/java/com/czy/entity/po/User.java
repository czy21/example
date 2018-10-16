package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import javax.persistence.Transient;

import java.util.Set;

/**
 * @Description User 实体
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public class User extends BaseEntity<User> {

    private Boolean Enabled;
    private String LoginName;
    private String Password;
    private String UserName;
    private String Email;
    private String Phone;
    private Boolean IsHeader;
    private String DepartmentId;

    /**
     * 用户所有角色值，用于shiro做角色权限的判断
     */
    @Transient
    private Set<String> roles;

    /**
     * 用户所有权限值，用于shiro做资源权限的判断
     */
    @Transient
    private Set<String> perms;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPerms() {
        return perms;
    }

    public void setPerms(Set<String> perms) {
        this.perms = perms;
    }

    public Boolean getEnabled() {
        return Enabled;
    }

    public void setEnabled(Boolean Enabled) {
        this.Enabled = Enabled;
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

    @Override
    public String toString() {
        return "User{" +
        ", Enabled=" + Enabled +
        ", LoginName=" + LoginName +
        ", Password=" + Password +
        ", UserName=" + UserName +
        ", Email=" + Email +
        ", Phone=" + Phone +
        ", IsHeader=" + IsHeader +
        ", DepartmentId=" + DepartmentId +
        "}";
    }
}
