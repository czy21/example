package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.team.infrastructure.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "ent_sys_user")
public class UserEntity extends BaseEntity {
    /**
     * 用户账号
     */
    private String account;

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
    private Integer status;
}