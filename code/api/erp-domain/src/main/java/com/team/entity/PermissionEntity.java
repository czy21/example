package com.team.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionEntity extends MybatisBaseEntity {
    private String key;

    private String name;

    private Integer sort;
}