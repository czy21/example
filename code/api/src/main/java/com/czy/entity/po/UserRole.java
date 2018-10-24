package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 用户角色表
 *
 * @author 陈昭宇
 * @since 2018-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity<UserRole> {
    @TableId
    private String UserRoleId;
    private String UserId;
    private String RoleId;
}
