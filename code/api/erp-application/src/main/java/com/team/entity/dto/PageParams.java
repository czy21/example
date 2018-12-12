package com.team.entity.dto;

import lombok.Data;

@Data
public class PageParams {
    private Integer pageIndex = 1;
    private Integer pageSize = 15;
    private Integer total;
}
