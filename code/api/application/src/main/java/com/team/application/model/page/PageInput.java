package com.team.application.model.page;

import lombok.Data;

@Data
public class PageInput {
    private Integer pageIndex = 1;
    private Integer pageSize = 20;
    private Integer total;
}
