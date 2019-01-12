package com.team.extension.entity;

import com.team.model.SimpleItemModel;
import com.team.repository.mybatis.system.DepartmentRepositoryMybatis;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentExtension {
    private static DepartmentRepositoryMybatis _dao;

    public DepartmentExtension(DepartmentRepositoryMybatis dao) {
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
