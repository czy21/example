package com.czy.core.extensions.entity;

import com.czy.core.extensions.SimpleItemModel;
import com.czy.dao.UserDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UserExtensions {

    private static UserDao _dao;

    public UserExtensions(UserDao dao) {
        _dao = dao;
    }

    public static List<SimpleItemModel> ConvertToSimple() {
        ArrayList simples = new ArrayList();
        HashMap<String, Object> hash = new HashMap<>();
        _dao.selectList(null).forEach((t) -> {
            SimpleItemModel temp = new SimpleItemModel();
            temp.setValue(t.getId());
            temp.setLabel(t.getUserName());
            simples.add(temp);
        });
        return simples;
    }
}
