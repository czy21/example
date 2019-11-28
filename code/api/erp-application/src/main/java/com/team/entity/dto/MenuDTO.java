package com.team.entity.dto;

import lombok.Data;

@Data
public class MenuDTO {
    private Long menuId;
    private Long parentId;
    private String menuName;
    private String icon;
    private String url;
    private Integer sort;
    private String remark;
}
