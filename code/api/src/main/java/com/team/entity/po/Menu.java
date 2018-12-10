package com.team.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @author 陈昭宇
 * @description 菜单权限表
 * @date 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId
    private String menuId;
    /**
     * 上级菜单
     */
    private String parentId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 菜单或权限地址
     */
    private String url;
    /**
     * 是否是菜单 0-权限,1-菜单
     */
    private Boolean isMenu;
    /**
     * 备注
     */
    private String remark;
}
