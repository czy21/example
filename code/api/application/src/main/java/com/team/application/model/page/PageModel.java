package com.team.application.model.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageModel<TEntity> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pageIndex;

    private Integer pageSize;

    private Integer total;

    private List<TEntity> list;


    public PageModel(List<TEntity> list) {
        this.list = list;
//        if (list instanceof Page) {
//            Page<TEntity> page = (Page<TEntity>) list;
//            this.pageIndex = page.getPageNum();
//            this.pageSize = page.getPageSize();
//            this.total = (int) page.getTotal();
//        } else {
//            this.total = list.size();
//        }
    }

    public PageModel(org.springframework.data.domain.Page<TEntity> page) {
        this.pageIndex = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.total = (int) page.getTotalElements();
        this.list = page.getContent();
    }

    public PageModel(Integer pageIndex, Integer pageSize, List<TEntity> list) {
        this(list);
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
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

    /*
     * PageHelper分页
     */
//    public static <TEntity> PageModel<TEntity> of(Page<TEntity> page) {
//        return new PageModel<>(page);
//    }

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