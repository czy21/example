package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 公司表
 *
 * @author 陈昭宇
 * @since 2018-10-20
 */
public class Company extends BaseEntity<Company> {

    @TableId
    private String CompanyId;
    private String CompanyName;
    private String ContactPerson;
    private String Location;
    private String Phone;
    private String Fax;
    private String Postcode;
    private String Comment;
    private Boolean Enabled;


    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String CompanyId) {
        this.CompanyId = CompanyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String ContactPerson) {
        this.ContactPerson = ContactPerson;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String Postcode) {
        this.Postcode = Postcode;
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
        return "Company{" +
                ", CompanyId=" + CompanyId +
                ", CompanyName=" + CompanyName +
                ", ContactPerson=" + ContactPerson +
                ", Location=" + Location +
                ", Phone=" + Phone +
                ", Fax=" + Fax +
                ", Postcode=" + Postcode +
                ", Comment=" + Comment +
                ", Enabled=" + Enabled +
                "}";
    }
}
