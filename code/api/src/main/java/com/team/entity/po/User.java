package com.team.entity.po;

import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户表
 *
 * @author 陈昭宇
 * @since 2018-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<User> {
    @TableId
    private String UserId;
    private String LoginName;
    private String Password;
    private String UserName;
    private String Email;
    private String Phone;
    private Boolean IsHeader;
    private String DepartmentId;
    private Boolean Enabled;
}
