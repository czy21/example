package com.team.application.model.dto;

import lombok.Data;

@Data
public class RoleDTO {
    private Long roleId;
    private String roleName;
    private String remark;
    private Boolean enabled;
}
