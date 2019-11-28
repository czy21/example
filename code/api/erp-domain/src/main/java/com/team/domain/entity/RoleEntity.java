package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.team.domain.infrastructure.MybatisBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "ent_sys_role")
public class RoleEntity extends MybatisBaseEntity {
    /**
     * 角色名称
     */
    private String name;
}