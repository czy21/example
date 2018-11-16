package com.team.model;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageSerializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageModel<TEntity> extends PageSerializable<TEntity> {

    private long pageIndex;

    private long pageSize;

    public PageModel(List<TEntity> list) {
        super(list);
        Page page = (Page) list;
        this.pageIndex = page.getPageNum();
        this.pageSize = page.getPageSize();
    }
}