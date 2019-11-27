package com.team.extension;

import com.team.model.SimpleItemModel;
import com.team.mapper.CompanyMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyExtension {
    private static CompanyMapper _dao;

    public CompanyExtension(CompanyMapper dao) {
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
