package com.team.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 角色表
 *
 * @author 陈昭宇
 * @since 2018-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity<Role> {
    @TableId
    private String RoleId;
    /**
     * 角色名称
     */
    private String RoleName;
    /**
     * 备注
     */
    private String Remark;
    private Boolean Enabled;
}
