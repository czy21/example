package com.team.application.model.vo;

import com.team.application.model.page.PageInput;
import lombok.Data;

@Data
public class PageUserInput {
    private PageInput page;
    private UserVO filter;
}
