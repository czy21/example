package com.team.cooperated.pocket;

public interface Option<T> {
    default T getValue() {
        return null;
    }

    default String getLabel() {
        return null;
    }
}
