package com.team.entity.vo;

import lombok.Data;

@Data
public class MenuDto {
    private String MenuId;
    private String ParentId;
    private String MenuName;
    private String Icon;
    private Integer Sort;
    private String Url;
    private Boolean IsMenu;
    private String Remark;
    private Boolean Enabled;
}
