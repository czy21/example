package com.team.extension;

import com.team.model.SimpleItemModel;
import com.team.system.RoleDao;
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
        List<SimpleItemModel> simples = new ArrayList<>();
        _dao.selectList(null).forEach((t) -> {
            SimpleItemModel temp = new SimpleItemModel();
            temp.setValue(t.getRoleId());
            temp.setValue(t.getRoleName());
            simples.add(temp);
        });

        return simples;
    }
}
