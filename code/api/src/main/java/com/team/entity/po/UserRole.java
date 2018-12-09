package com.team.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户角色表
 *
 * @author 陈昭宇
 * @date 2018-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("user_role_id")
    private String userRoleId;
    @TableField("user_id")
    private String userId;
    @TableField("role_id")
    private String roleId;
}
