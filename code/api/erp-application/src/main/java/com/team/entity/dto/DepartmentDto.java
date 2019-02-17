package com.team.entity.dto;

import lombok.Data;

@Data
public class DepartmentDto {
    private Long departmentId;
    private Long parentId;
    private String departmentName;
    private String phone;
    private String remark;
    private Long companyId;
    private Boolean enabled;
}
