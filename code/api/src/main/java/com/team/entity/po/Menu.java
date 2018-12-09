package com.team.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 菜单权限表
 *
 * @author 陈昭宇
 * @date 2018-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("menu_id")
    private String menuId;
    /**
     * 上级菜单
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;
    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;
    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 菜单或权限地址
     */
    @TableField("url")
    private String url;
    /**
     * 是否是菜单 0-权限,1-菜单
     */
    @TableField("is_menu")
    private Boolean isMenu;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
