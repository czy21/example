package com.team.entity;

import com.team.infrastructure.MybatisBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermissionEntity extends MybatisBaseEntity {
    private String roleId;

    private String permissionKey;
}