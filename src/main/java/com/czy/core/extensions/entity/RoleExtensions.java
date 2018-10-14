package com.czy.core.extensions.entity;

import com.czy.core.extensions.SimpleItemModel;
import com.czy.dao.RoleDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleExtensions {

    private static RoleDao _dao;

    public RoleExtensions(RoleDao dao) {
        _dao = dao;
    }

    public static List<SimpleItemModel> ConvertToSimple() {
        ArrayList simples = new ArrayList();
        _dao.selectList(null).forEach((t) -> {
            SimpleItemModel temp = new SimpleItemModel();
            temp.setValue(t.getId());
            temp.setValue(t.getName());
            simples.add(temp);
        });

        return simples;
    }
}
