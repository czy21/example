package com.team.entity.vo;

import lombok.Data;

@Data
public class PageParams {
    private long pageIndex;
    private long pageSize;
    private long total;
}
