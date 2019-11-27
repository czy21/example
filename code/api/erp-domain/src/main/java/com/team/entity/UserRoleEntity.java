package com.team.entity;

import lombok.Data;

@Data
public class UserRoleEntity extends MybatisBaseEntity {
    private String userId;

    private String roleId;
}