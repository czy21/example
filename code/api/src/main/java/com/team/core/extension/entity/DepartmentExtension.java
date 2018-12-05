package com.team.core.extension.entity;

import com.team.core.model.SimpleItemModel;
import com.team.dao.DepartmentDao;
import com.team.dao.MenuDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentExtension {
    private static DepartmentDao _dao;

    public DepartmentExtension(DepartmentDao dao) {
        _dao = dao;
    }

    public static List<SimpleItemModel> convertToSimple() {

        List<SimpleItemModel> simples = new ArrayList<>();
        _dao.selectList(null).forEach((t) -> {
            SimpleItemModel temp = new SimpleItemModel();
            temp.setValue(t.getDepartmentId());
            temp.setLabel(t.getDepartmentName());
            simples.add(temp);
        });
        return simples;
    }
}
