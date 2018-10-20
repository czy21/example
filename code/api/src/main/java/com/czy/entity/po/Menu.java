package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 菜单权限表
 *
 * @author 陈昭宇
 * @since 2018-10-20
 */
public class Menu extends BaseEntity<Menu> {

    @TableId
    private String MenuId;
    private String ParentId;
    private String MenuName;
    private String Icon;
    private Integer Sort;
    private String Url;
    private Boolean IsMenu;
    private String Comment;
    private Boolean Enabled;


    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String MenuId) {
        this.MenuId = MenuId;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String ParentId) {
        this.ParentId = ParentId;
    }

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String MenuName) {
        this.MenuName = MenuName;
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

    public Boolean getEnabled() {
        return Enabled;
    }

    public void setEnabled(Boolean Enabled) {
        this.Enabled = Enabled;
    }

    @Override
    public String toString() {
        return "Menu{" +
                ", MenuId=" + MenuId +
                ", ParentId=" + ParentId +
                ", MenuName=" + MenuName +
                ", Icon=" + Icon +
                ", Sort=" + Sort +
                ", Url=" + Url +
                ", IsMenu=" + IsMenu +
                ", Comment=" + Comment +
                ", Enabled=" + Enabled +
                "}";
    }
}
