package com.team.model;

import com.team.entity.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchFunctionModel extends PageParams {
    public String functionName;
    public String functionCode;
}
