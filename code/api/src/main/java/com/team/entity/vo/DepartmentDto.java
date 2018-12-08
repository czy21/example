package com.team.entity.vo;

import lombok.Data;

@Data
public class DepartmentDto {
    private String DepartmentId;
    private String ParentId;
    private String DepartmentName;
    private String Phone;
    private String Remark;
    private String CompanyId;
    private Boolean Enabled;
}
