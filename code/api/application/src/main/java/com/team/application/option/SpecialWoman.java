package com.team.application.option;

import com.team.cooperated.annotation.OptionName;
import com.team.cooperated.model.simple.SimpleItemModel;
import com.team.cooperated.option.OptionProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@OptionName(value = "woman")
public class SpecialWoman implements OptionProvider<String> {
    @Override
    public List<SimpleItemModel<String>> obtain() {
        return null;
    }
}
