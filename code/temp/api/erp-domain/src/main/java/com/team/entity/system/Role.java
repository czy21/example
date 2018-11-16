package com.team.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


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
