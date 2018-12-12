package com.team.entity.dto;

import lombok.Data;

@Data
public class DepartmentDto {
    private String departmentId;
    private String parentId;
    private String departmentName;
    private String phone;
    private String remark;
    private String companyId;
    private Boolean enabled;
}
