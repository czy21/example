package com.team.extension;

import com.team.model.SimpleItemModel;
import com.team.system.UserDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserExtensions {

    private static UserDao _dao;

    public UserExtensions(UserDao dao) {
        _dao = dao;
    }

    public static List<SimpleItemModel> ConvertToSimple() {
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
