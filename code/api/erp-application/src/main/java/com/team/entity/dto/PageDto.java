package com.team.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<TEntity> {
    private PageParams page;
    private List<TEntity> list;
}