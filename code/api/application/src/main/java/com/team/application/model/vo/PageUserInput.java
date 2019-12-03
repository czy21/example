package com.team.application.model.vo;

import com.team.application.model.page.PageInput;
import lombok.Data;

import java.util.List;

@Data
public class PageUserInput {
    private PageInput page;
    private List<UserVO> filter;
}
