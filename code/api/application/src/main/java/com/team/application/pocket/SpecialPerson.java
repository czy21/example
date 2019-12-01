package com.team.application.pocket;

import com.team.cooperated.model.simple.SimpleItemModel;
import com.team.cooperated.pocket.PocketProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "person")
public class SpecialPerson implements PocketProvider<String> {
    @Override
    public List<SimpleItemModel<String>> obtain() {
        return List.of(SimpleItemModel.of("张三", "1"), SimpleItemModel.of("李四", "1"));
    }
}
