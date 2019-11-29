package com.team.application.model.dto;

import lombok.Data;

@Data
public class DepartmentDTO {
    private Long departmentId;
    private Long parentId;
    private String departmentName;
    private String phone;
    private String remark;
    private Long companyId;
    private Boolean enabled;
}
