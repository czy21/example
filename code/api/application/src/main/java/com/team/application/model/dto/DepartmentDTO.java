package com.team.application.model.dto;

import lombok.Data;

@Data
public class DepartmentDTO {
    private String id;
    private String parentId;
    private String name;
    private String phone;
    private String remark;
    private String companyId;
    private Boolean enabled;
}
