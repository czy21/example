package com.team.application.model.vo;

import lombok.Data;

@Data
public class PageVO {
    private Integer pageIndex = 1;
    private Integer pageSize = 20;
    private Integer total;
}
