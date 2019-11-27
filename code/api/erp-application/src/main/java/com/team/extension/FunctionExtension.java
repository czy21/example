package com.team.extension;

import com.team.model.SimpleItemModel;
import com.team.mapper.PermissionMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FunctionExtension {
    private static PermissionMapper _dao;

    public FunctionExtension(PermissionMapper dao) {
        _dao = dao;
    }

    public static List<SimpleItemModel> convertToSimple() {
        List<SimpleItemModel> simples = new ArrayList<>();
        _dao.selectList(null).forEach((t) -> {
            SimpleItemModel temp = new SimpleItemModel();
            temp.setValue(t.getFunctionId());
            temp.setLabel(t.getFunctionName());
            simples.add(temp);
        });
        return simples;
    }
}
