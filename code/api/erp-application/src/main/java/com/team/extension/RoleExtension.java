package com.team.extension;

import com.team.model.SimpleItemModel;
import com.team.mapper.RoleMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleExtension {

    private static RoleMapper _dao;

    public RoleExtension(RoleMapper dao) {
        _dao = dao;
    }

    public static List<SimpleItemModel> convertToSimple() {
        List<SimpleItemModel> simples = new ArrayList<>();
        _dao.selectList(null).forEach((t) -> {
            SimpleItemModel temp = new SimpleItemModel();
            temp.setValue(t.getRoleId());
            temp.setLabel(t.getRoleName());
            simples.add(temp);
        });

        return simples;
    }
}
