package com.team.entity.page;

import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageModel<TEntity> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Integer pageIndex;

    protected Integer pageSize;

    protected Integer total;

    protected List<TEntity> list;

    public PageModel(List<TEntity> list) {
        this.list = list;
        if (list instanceof Page) {
            Page<TEntity> page = (Page<TEntity>) list;
            this.pageIndex = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = (int) page.getTotal();
        } else {
            this.total = list.size();
        }
    }

    public PageModel(Integer pageIndex, Integer pageSize, List<TEntity> list) {
        this(list);
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public static <TEntity> PageModel<TEntity> of(Page<TEntity> page) {
        return new PageModel<>(page);
    }

    public static <TEntity> PageModel<TEntity> of(Integer pageIndex, Integer pageSize, List<TEntity> list) {
        return new PageModel<>(pageIndex, pageSize, list);
    }

}