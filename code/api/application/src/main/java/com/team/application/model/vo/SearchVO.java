package com.team.application.model.vo;

import lombok.Data;

@Data
public class SearchVO<T> {
    PageVO page = new PageVO();
    T filter;
}
