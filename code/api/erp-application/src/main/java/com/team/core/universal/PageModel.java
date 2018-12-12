package com.team.core.universal;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageSerializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageModel<TEntity> extends PageSerializable<TEntity> {

    private Integer pageIndex;

    private Integer pageSize;

    public PageModel(List<TEntity> list) {
        super(list);
        Page page = (Page) list;
        this.pageIndex = page.getPageNum();
        this.pageSize = page.getPageSize();
    }
}