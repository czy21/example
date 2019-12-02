package com.team.application.model.dto;

import com.team.application.model.page.PageInput;
import lombok.Data;

import java.util.List;

@Data
public class PageDTO<TEntity> {
    private PageInput page;
    private List<TEntity> list;
}