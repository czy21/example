package com.team.entity;

import com.team.infrastructure.MybatisBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleEntity extends MybatisBaseEntity {
    /**
     * 角色名称
     */
    private String name;

    private String remark;
}