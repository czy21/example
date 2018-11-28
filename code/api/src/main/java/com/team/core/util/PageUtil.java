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
        int pageCount = this.total / this.pageSize;
        int fromIndex = pageSize * (pageIndex - 1);
        int toIndex = fromIndex + pageSize;
        if (toIndex > this.total) {
            toIndex = this.total;
        }
        if (pageIndex > pageCount + 1) {
            this.pageIndex = pageCount + 1;
            fromIndex = pageCount + 1;
            toIndex = this.total;
        }
        this.list = list.subList(fromIndex, toIndex);
    }
}
