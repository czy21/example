package com.team.application.model.dto;

import lombok.Data;

@Data
public class MenuDTO {
    private String id;
    private String parentId;
    private String name;
    private String icon;
    private String url;
    private Integer sort;
    private String remark;
}
