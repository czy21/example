package com.team.model;

import com.team.entity.vo.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchPermissionModel extends PageParams {

    private String menuId;


}
