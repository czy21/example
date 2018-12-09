package com.team.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 部门表
 *
 * @author 陈昭宇
 * @date 2018-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Department extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("department_id")
    private String departmentId;
    /**
     * 上级部门
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 部门名称
     */
    @TableField("department_name")
    private String departmentName;
    /**
     * 部门电话
     */
    @TableField("phone")
    private String phone;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    /**
     * 所属公司
     */
    @TableField("company_id")
    private String companyId;
    @TableField("enabled")
    private Boolean enabled;
}
