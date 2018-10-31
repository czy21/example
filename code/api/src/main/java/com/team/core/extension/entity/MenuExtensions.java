package com.team.core.extension.entity;


import com.team.core.model.SimpleItemModel;
import com.team.dao.MenuDao;
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

        List<SimpleItemModel> simples = new ArrayList<>();
        _dao.selectList(null).forEach((t) -> {
            SimpleItemModel temp = new SimpleItemModel();
            temp.setValue(t.getMenuId());
            temp.setLabel(t.getMenuName());
            simples.add(temp);
        });
        return simples;
    }
}
