package com.team.model;

import lombok.Data;

@Data
public class SimpleItemModel {
    private Long value;
    private String label;
    private Long parentId;
}
