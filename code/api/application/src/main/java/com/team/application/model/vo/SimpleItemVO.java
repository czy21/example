package com.team.application.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class SimpleItemVO<T> {
    private String label;
    private T value;
    private T parent;
    private List<SimpleItemVO<T>> children;
}
