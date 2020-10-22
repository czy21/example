package com.team.application.model.dto;

import com.team.application.model.vo.PageVO;
import lombok.Data;

import java.util.List;

@Data
public class PageDTO<TEntity> {
    private PageVO page;
    private List<TEntity> list;
}