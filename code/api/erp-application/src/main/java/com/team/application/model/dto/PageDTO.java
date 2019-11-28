package com.team.application.model.dto;

import com.team.application.model.page.PageParams;
import lombok.Data;

import java.util.List;

@Data
public class PageDTO<TEntity> {
    private PageParams page;
    private List<TEntity> list;
}