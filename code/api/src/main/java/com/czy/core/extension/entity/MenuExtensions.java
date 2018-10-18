package com.czy.core.extension.entity;


import com.czy.core.model.SimpleItemModel;
import com.czy.dao.MenuDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuExtensions {

    private static MenuDao _dao;

    public MenuExtensions(MenuDao dao) {
        _dao = dao;
    }

    public static List<SimpleItemModel> ConvertToSimple() {

        ArrayList simples = new ArrayList();
        _dao.selectList(null).forEach((t) -> {
            SimpleItemModel temp = new SimpleItemModel();
            temp.setValue(t.getId());
            temp.setLabel(t.getName());
            simples.add(temp);
        });
        return simples;
    }
}
