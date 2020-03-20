package com.team.application.model.dto;

import lombok.Data;

@Data
public class PermissionDTO {

    private String functionId;

    private String functionCode;

    private String functionName;

    private Integer sort;

    private String remark;

}
