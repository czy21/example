package com.team.application.model.dto;

import lombok.Data;

@Data
public class RoleDTO {
    private String id;
    private String name;
    private String remark;
    private Boolean enabled;
}
