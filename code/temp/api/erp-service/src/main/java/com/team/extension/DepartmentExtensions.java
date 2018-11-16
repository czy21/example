package com.team.extension;

import com.team.model.SimpleItemModel;
import com.team.system.DepartmentDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentExtensions {
    private static DepartmentDao _dao;

    public DepartmentExtensions(DepartmentDao dao) {
        _dao = dao;
    }

    public static List<SimpleItemModel> ConvertToSimple() {

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
