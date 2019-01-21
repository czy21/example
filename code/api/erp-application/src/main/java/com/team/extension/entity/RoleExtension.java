package com.team.extension.entity;

import com.team.model.SimpleItemModel;
import com.team.repository.mybatis.system.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleExtension {

    private static RoleRepository _dao;

    public RoleExtension(RoleRepository dao) {
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
