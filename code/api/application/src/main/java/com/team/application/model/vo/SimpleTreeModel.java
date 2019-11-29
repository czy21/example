package com.team.application.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleTreeModel extends SimpleItemModel {
    private Object children;
}
