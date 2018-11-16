package com.team.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 部门表
 *
 * @author 陈昭宇
 * @since 2018-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Department extends BaseEntity<Department> {
    @TableId
    private String DepartmentId;
    /**
     * 上级部门
     */
    private String ParentId;
    /**
     * 部门名称
     */
    private String DepartmentName;
    /**
     * 部门电话
     */
    private String Phone;
    /**
     * 备注
     */
    private String Remark;
    /**
     * 所属公司
     */
    private String CompanyId;
    private Boolean Enabled;
}
