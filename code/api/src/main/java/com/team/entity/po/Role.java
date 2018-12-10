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
 * @description 角色表
 * @date 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity<Role> {

    private static final long serialVersionUID = 1L;

    @TableId
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 备注
     */
    private String remark;
    private Boolean enabled;
}
