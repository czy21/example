package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.team.infrastructure.base.MybatisBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "met_sys_permission")
public class PermissionEntity extends MybatisBaseEntity {
    private String key;

    private String name;

    private Integer sort;
}