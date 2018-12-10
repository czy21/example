package com.team.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @author 陈昭宇
 * @description 用户表
 * @date 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

    @TableId
    private String userId;
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
     * 用户电话
     */
    private String phone;
    /**
     * 是否是部长
     */
    private Boolean isHeader;
    /**
     * 所属部门
     */
    private String departmentId;
    private Boolean enabled;
}
