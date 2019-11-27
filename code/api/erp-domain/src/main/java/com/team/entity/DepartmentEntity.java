package com.team.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
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

    private String remark;
}