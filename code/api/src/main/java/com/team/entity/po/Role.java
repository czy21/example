package com.team.entity.po;

import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;


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
