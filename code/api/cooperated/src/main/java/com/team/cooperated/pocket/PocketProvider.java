package com.team.cooperated.pocket;

import com.team.cooperated.model.simple.SimpleItemModel;

import java.util.List;

public interface PocketProvider {
    List<SimpleItemModel> obtain();
}
