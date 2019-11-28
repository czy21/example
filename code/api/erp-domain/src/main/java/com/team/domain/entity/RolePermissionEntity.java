package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.team.domain.infrastructure.MybatisBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "ent_sys_role_permission")
public class RolePermissionEntity extends MybatisBaseEntity {
    private String roleId;

    private String permissionKey;
}