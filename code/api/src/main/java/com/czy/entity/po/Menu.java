package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 菜单权限表
 *
 * @author 陈昭宇
 * @since 2018-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
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
}
