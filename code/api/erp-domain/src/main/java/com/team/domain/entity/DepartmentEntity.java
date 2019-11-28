package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.team.domain.infrastructure.MybatisBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "ent_sys_department")
public class DepartmentEntity extends MybatisBaseEntity {
    /**
     * 上级部门
     */
    private String parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 所属公司
     */
    private String companyId;
}