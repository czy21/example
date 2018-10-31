package com.team.entity.po;

import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;


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
