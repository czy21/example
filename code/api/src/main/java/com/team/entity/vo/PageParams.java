package com.team.entity.vo;

import lombok.Data;

@Data
public class PageParams {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer total;
}
