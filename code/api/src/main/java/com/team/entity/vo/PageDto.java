package com.team.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<TEntity> {
    private PageParams page;
    private List<TEntity> list;
}