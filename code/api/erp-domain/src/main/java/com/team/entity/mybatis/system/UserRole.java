package com.team.entity.mybatis.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 陈昭宇
 * @description 用户角色表
 * @date 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity<UserRole> {

    private static final long serialVersionUID = 1L;

    @TableId
    private String userRoleId;
    private String userId;
    private String roleId;
}
