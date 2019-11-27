package com.team.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermissionEntity extends MybatisBaseEntity {
    private String roleId;

    private String permissionKey;
}