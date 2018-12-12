package com.team.entity.dto;

import lombok.Data;

@Data
public class MenuDto {
    private String menuId;
    private String parentId;
    private String menuName;
    private String icon;
    private String url;
    private Integer sort;
    private Boolean isMenu;
    private String remark;
}
