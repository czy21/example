package com.team.extension;

import com.team.model.SimpleItemModel;
import com.team.repository.mybatis.system.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentExtension {
    private static DepartmentRepository _dao;

    public DepartmentExtension(DepartmentRepository dao) {
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
