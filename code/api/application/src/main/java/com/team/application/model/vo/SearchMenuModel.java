package com.team.application.model.vo;

import com.team.application.model.page.PageInput;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchMenuModel extends PageInput {
    private String menuName;
}
