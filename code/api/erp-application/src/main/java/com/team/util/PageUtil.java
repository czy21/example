package com.team.util;

import com.team.entity.page.PageModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageUtil<T> extends PageModel<T> {

    public PageUtil(Integer pageIndex, Integer pageSize, List<T> list) {
        super(pageIndex, pageSize, list);
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

    public static <T> PageUtil<T> of(Integer pageIndex, Integer pageSize, List<T> list) {
        return new PageUtil<>(pageIndex, pageSize, list);
    }
}
