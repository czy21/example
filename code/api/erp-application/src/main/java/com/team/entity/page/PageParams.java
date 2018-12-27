package com.team.entity.page;

import lombok.Data;

@Data
public class PageParams {
    private Integer pageIndex = 1;
    private Integer pageSize = 20;
    private Integer total;
}
