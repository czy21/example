package com.team.core.util;

import lombok.Data;

import java.util.List;

@Data
public class PageUtil<T> {

    private Integer pageIndex;
    private Integer pageSize;
    private Integer total;
    private List<T> list;

    public PageUtil(Integer pageIndex, Integer pageSize, List<T> list) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = list.size();
        this.list = list.subList(pageSize * (pageIndex - 1), ((pageSize * pageIndex) > total ? total : (pageSize * pageIndex)));
    }
}
