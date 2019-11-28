package com.team.entity.dto;

import lombok.Data;

@Data
public class RoleDTO {
    private Long roleId;
    private String roleName;
    private String remark;
    private Boolean enabled;
}
