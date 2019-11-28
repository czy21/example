package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.team.domain.infrastructure.base.MybatisBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "ent_sys_user_role")
public class UserRoleEntity extends MybatisBaseEntity {
    private String userId;

    private String roleId;
}