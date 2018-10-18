package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @Description Company 实体
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public class Company extends BaseEntity<Company> {

    private Boolean Enabled;
    private String Name;
    private String ContactPerson;
    private String Location;
    private String Phone;
    private String Fax;
    private String Postcode;
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

    @Override
    public String toString() {
        return "Company{" +
        ", Enabled=" + Enabled +
        ", Name=" + Name +
        ", ContactPerson=" + ContactPerson +
        ", Location=" + Location +
        ", Phone=" + Phone +
        ", Fax=" + Fax +
        ", Postcode=" + Postcode +
        ", Comment=" + Comment +
        "}";
    }
}
