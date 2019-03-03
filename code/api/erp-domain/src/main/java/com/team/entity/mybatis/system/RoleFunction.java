package com.team.entity.mybatis.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 陈昭宇
 * @description 角色权限表
 * @date 2019-03-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleFunction extends BaseEntity<RoleFunction> {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long roleFunctionId;
    private Long roleId;
    private Long functionId;
}
