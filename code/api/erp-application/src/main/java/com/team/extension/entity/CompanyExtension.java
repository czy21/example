package com.team.extension.entity;

import com.team.model.SimpleItemModel;
import com.team.dao.system.CompanyDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyExtension {
    private static CompanyDao _dao;

    public CompanyExtension(CompanyDao dao) {
        _dao = dao;
    }

    public static List<SimpleItemModel> convertToSimple() {
        List<SimpleItemModel> simples = new ArrayList<>();
        _dao.selectList(null).forEach((t) -> {
            SimpleItemModel temp = new SimpleItemModel();
            temp.setValue(t.getCompanyId());
            temp.setLabel(t.getCompanyName());
            simples.add(temp);
        });
        return simples;
    }
}
