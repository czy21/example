package com.team.entity.vo;

import lombok.Data;

@Data
public class RoleDto {
    private String roleId;
    private String roleName;
    private String remark;
    private Boolean enabled;
}
