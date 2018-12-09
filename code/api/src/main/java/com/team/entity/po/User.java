package com.team.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户表
 *
 * @author 陈昭宇
 * @date 2018-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private String userId;
    /**
     * 用户账号
     */
    @TableField("login_name")
    private String loginName;
    /**
     * 用户密码
     */
    @TableField("password")
    private String password;
    /**
     * 用户姓名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 用户电话
     */
    @TableField("phone")
    private String phone;
    /**
     * 是否是部长
     */
    @TableField("is_header")
    private Boolean isHeader;
    /**
     * 所属部门
     */
    @TableField("department_id")
    private String departmentId;
    @TableField("enabled")
    private Boolean enabled;
}
