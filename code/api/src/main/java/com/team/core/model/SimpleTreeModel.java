package com.team.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleTreeModel extends SimpleItemModel {
    private Object Children;
}
