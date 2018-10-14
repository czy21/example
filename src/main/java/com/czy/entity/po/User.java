package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.stereotype.Component;

/**
 * @Description User 实体
 * @Author 陈昭宇
 * @Date 2018-09-24
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
