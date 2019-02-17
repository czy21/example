package com.team.entity.mybatis.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


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
    private Long menuId;
    /**
     * 上级菜单
     */
    private Long parentId;
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
