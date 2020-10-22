package com.team.application.model.page;


import lombok.Data;

import java.util.List;

@Data
public class PageModel<TEntity> {

    private long pageIndex;

    private long pageSize;

    private long total;

    private List<TEntity> list;


    public PageModel(com.baomidou.mybatisplus.extension.plugins.pagination.Page<TEntity> page) {
        this.list = page.getRecords();
        this.pageIndex = page.getCurrent();
        this.pageSize = page.getSize();
        this.total = page.getTotal();
        this.total = list.size();
    }

    public PageModel(org.springframework.data.domain.Page<TEntity> page) {
        this.pageIndex = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.total = (int) page.getTotalElements();
        this.list = page.getContent();
    }

    public PageModel(Integer pageIndex, Integer pageSize, List<TEntity> list) {
        this.list = list;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        long pageCount = this.total / this.pageSize;
        long fromIndex = pageSize * (pageIndex - 1);
        long toIndex = fromIndex + pageSize;
        if (toIndex > this.total) {
            toIndex = this.total;
        }
        if (pageIndex > pageCount + 1) {
            this.pageIndex = pageCount + 1;
            fromIndex = pageCount + 1;
            toIndex = this.total;
        }
        this.list = list.subList((int) fromIndex, (int) toIndex);
    }

    /*
     * Mybatis Plus分页
     */
    public static <TEntity> PageModel<TEntity> of(com.baomidou.mybatisplus.extension.plugins.pagination.Page<TEntity> page) {
        return new PageModel<>(page);
    }

    /*
     * Mongo分页
     */
    public static <TEntity> PageModel<TEntity> of(org.springframework.data.domain.Page<TEntity> page) {
        return new PageModel<>(page);
    }

    /*
     * 物理分页
     */
    public static <TEntity> PageModel<TEntity> of(Integer pageIndex, Integer pageSize, List<TEntity> list) {
        return new PageModel<>(pageIndex, pageSize, list);
    }

}