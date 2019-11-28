package com.team.application.model.vo;

import com.team.application.model.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchFunctionModel extends PageParams {
    public String functionName;
    public String functionCode;
}
