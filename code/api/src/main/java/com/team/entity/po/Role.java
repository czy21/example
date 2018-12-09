package com.team.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 角色表
 *
 * @author 陈昭宇
 * @date 2018-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("role_id")
    private String roleId;
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    @TableField("enabled")
    private Boolean enabled;
}
