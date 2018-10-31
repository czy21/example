package com.team.entity.po;

import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 部门表
 *
 * @author 陈昭宇
 * @since 2018-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Department extends BaseEntity<Department> {
    @TableId
    private String DepartmentId;
    private String ParentId;
    private String DepartmentName;
    private String Phone;
    private String Comment;
    private String CompanyId;
    private Boolean Enabled;
}
