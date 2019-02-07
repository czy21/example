package com.team.model;

import com.team.entity.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SeachLogModel extends PageParams {
    private String addedTimeSort = "desc";
}
