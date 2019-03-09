package com.team.entity.dto;

import lombok.Data;

@Data
public class FunctionDto {

    private Long functionId;

    private String functionCode;

    private String functionName;

    private Integer sort;

    private String remark;

}
