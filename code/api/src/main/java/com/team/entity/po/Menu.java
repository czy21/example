package com.team.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 菜单权限表
 *
 * @author 陈昭宇
 * @since 2018-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity<Menu> {
    @TableId
    private String MenuId;
    /**
     * 上级菜单
     */
    private String ParentId;
    /**
     * 菜单名称
     */
    private String MenuName;
    /**
     * 菜单图标
     */
    private String Icon;
    /**
     * 排序
     */
    private Integer Sort;
    /**
     * 菜单或权限地址
     */
    private String Url;
    /**
     * 是否是菜单 0-权限,1-菜单
     */
    private Boolean IsMenu;
    /**
     * 备注
     */
    private String Remark;
    private Boolean Enabled;
}
