package com.team.application.model.vo;

import com.team.application.model.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchMenuModel extends PageParams {
    private String menuName;
}
