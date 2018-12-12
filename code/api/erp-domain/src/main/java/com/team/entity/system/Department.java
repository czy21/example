package com.team.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 陈昭宇
 * @description 部门表
 * @date 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Department extends BaseEntity<Department> {

    private static final long serialVersionUID = 1L;

    @TableId
    private String departmentId;
    /**
     * 上级部门
     */
    private String parentId;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 部门电话
     */
    private String phone;
    /**
     * 备注
     */
    private String remark;
    /**
     * 所属公司
     */
    private String companyId;
    private Boolean enabled;
}
