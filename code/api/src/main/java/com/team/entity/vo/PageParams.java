package com.team.entity.vo;

import lombok.Data;

@Data
public class PageParams {
    private Integer pageIndex = 1;
    private Integer pageSize = 15;
    private Integer total;
}
