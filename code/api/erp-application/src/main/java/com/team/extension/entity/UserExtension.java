package com.team.extension.entity;

import com.team.model.SimpleItemModel;
import com.team.repository.mybatis.system.UserRepositoryMybatis;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserExtension  {

    private static UserRepositoryMybatis _dao;

    public UserExtension(UserRepositoryMybatis dao) {
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
