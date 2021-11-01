package com.team.application.pocket;

import com.team.cooperated.annotation.PocketName;
import com.team.cooperated.model.simple.SimpleItemModel;
import com.team.cooperated.pocket.PocketProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PocketName(value = "woman")
public class SpecialWoman implements PocketProvider<String> {
    @Override
    public List<SimpleItemModel<String>> obtain() {
        return null;
    }
}
