package com.team.extension.entity;

import com.team.model.SimpleItemModel;
import com.team.dao.system.UserDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserExtension  {

    private static UserDao _dao;

    public UserExtension(UserDao dao) {
        _dao = dao;
    }

    public static List<SimpleItemModel> convertToSimple() {
        List<SimpleItemModel> simples = new ArrayList<>();
        _dao.selectList(null).forEach((t) -> {
            SimpleItemModel temp = new SimpleItemModel();
            temp.setValue(t.getUserId());
            temp.setLabel(t.getUserName());
            simples.add(temp);
        });
        return simples;
    }
}
