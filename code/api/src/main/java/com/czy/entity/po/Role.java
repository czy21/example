package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 角色表
 *
 * @author 陈昭宇
 * @since 2018-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity<Role> {
    @TableId
    private String RoleId;
    private String RoleName;
    private String Comment;
    private Boolean Enabled;
}
