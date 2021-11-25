package com.team.application.option;

import com.team.cooperated.annotation.OptionName;
import com.team.cooperated.model.simple.SimpleItemModel;
import com.team.cooperated.option.OptionProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@OptionName(value = "person")
public class SpecialPerson implements OptionProvider<String> {
    @Override
    public List<SimpleItemModel<String>> obtain() {
        return List.of(SimpleItemModel.of("张三", "1"), SimpleItemModel.of("李四", "2"));
    }
}
