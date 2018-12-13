package com.team.entity.dto;

import com.team.entity.page.PageParams;
import lombok.Data;

import java.util.List;

@Data
public class PageDto<TEntity> {
    private PageParams page;
    private List<TEntity> list;
}