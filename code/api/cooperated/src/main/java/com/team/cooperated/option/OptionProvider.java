package com.team.cooperated.option;

import com.team.cooperated.model.simple.SimpleItemModel;

import java.util.List;

public interface OptionProvider<T> {
    List<SimpleItemModel<T>> obtain();
}
