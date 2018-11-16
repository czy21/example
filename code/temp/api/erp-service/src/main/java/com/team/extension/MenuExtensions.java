package com.team.extension;


import com.team.model.SimpleItemModel;
import com.team.system.MenuDao;
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
            temp.setParentId(t.getParentId());
            simples.add(temp);
        });
        return simples;
    }
}
