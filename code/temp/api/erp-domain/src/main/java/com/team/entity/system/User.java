package com.team.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户表
 *
 * @author 陈昭宇
 * @since 2018-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<User> {
    @TableId
    private String UserId;
    /**
     * 用户账号
     */
    private String LoginName;
    /**
     * 用户密码
     */
    private String Password;
    /**
     * 用户姓名
     */
    private String UserName;
    /**
     * 用户邮箱
     */
    private String Email;
    /**
     * 用户电话
     */
    private String Phone;
    /**
     * 是否是部长
     */
    private Boolean IsHeader;
    /**
     * 所属部门
     */
    private String DepartmentId;
    private Boolean Enabled;
}
