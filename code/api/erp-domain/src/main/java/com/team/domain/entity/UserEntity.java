package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.team.domain.infrastructure.MybatisBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "ent_sys_user")
public class UserEntity extends MybatisBaseEntity {
    /**
     * 用户账号
     */
    private String loginName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 所属部门
     */
    private String departmentId;
}