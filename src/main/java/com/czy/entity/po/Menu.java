package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Description Menu 实体
 * @Author 陈昭宇
 * @Date 2018-09-24
 */
public class Menu extends BaseEntity<Menu> {

    private Boolean Enabled;
    private String ParentId;
    private String Name;
    private String Icon;
    private Integer Sort;
    private String Url;
    private Boolean IsMenu;
    private String Comment;


    public Boolean getEnabled() {
        return Enabled;
    }

    public void setEnabled(Boolean Enabled) {
        this.Enabled = Enabled;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String ParentId) {
        this.ParentId = ParentId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String Icon) {
        this.Icon = Icon;
    }

    public Integer getSort() {
        return Sort;
    }

    public void setSort(Integer Sort) {
        this.Sort = Sort;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public Boolean getIsMenu() {
        return IsMenu;
    }

    public void setIsMenu(Boolean IsMenu) {
        this.IsMenu = IsMenu;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    @Override
    public String toString() {
        return "Menu{" +
        ", Enabled=" + Enabled +
        ", ParentId=" + ParentId +
        ", Name=" + Name +
        ", Icon=" + Icon +
        ", Sort=" + Sort +
        ", Url=" + Url +
        ", IsMenu=" + IsMenu +
        ", Comment=" + Comment +
        "}";
    }
}
