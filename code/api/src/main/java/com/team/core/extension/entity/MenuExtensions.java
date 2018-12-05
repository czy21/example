package com.team.core.extension.entity;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.core.extension.StringExtension;
import com.team.core.model.SimpleItemModel;
import com.team.core.model.SimpleTreeModel;
import com.team.dao.MenuDao;
import com.team.entity.po.Menu;
import com.team.entity.vo.MenuDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MenuExtensions {

    private static MenuDao _dao;

    public MenuExtensions(MenuDao dao) {
        _dao = dao;
    }

    public static List<SimpleItemModel> ConvertToSimple() {

        List<SimpleItemModel> simples = new ArrayList<>();
        _dao.selectList(new QueryWrapper<Menu>().eq("IsMenu", true)).forEach((t) -> {
            SimpleItemModel temp = new SimpleItemModel();
            temp.setValue(t.getMenuId());
            temp.setLabel(t.getMenuName());
            temp.setParentId(t.getParentId());
            simples.add(temp);
        });
        return simples;
    }

    /**
     * 根据父节点CID获取所有子节点
     */
    public static List<Menu> GetSons(List<Menu> list, String parentId) {
        if (StringUtils.isEmpty(parentId)) {
            return list;
        }
        List<Menu> query = list.stream().filter(t -> t.getMenuId().equals(parentId)).collect(Collectors.toList());
        query.addAll(GetSonList(list, parentId));
        query.sort((o1, o2) -> o2.getIsMenu().compareTo(o1.getIsMenu()));
        return query;
    }

    private static List<Menu> GetSonList(List<Menu> list, String parentId) {
        List<Menu> menus = new ArrayList<>();
        list.forEach(t -> {
            if (t.getParentId().equals(parentId)) {
                menus.addAll(GetSons(list, t.getMenuId()));
            }
        });
        return menus;
    }

    public static List<SimpleTreeModel> TransPermissionToRadioGroups() {
        QueryWrapper<Menu> queryMenu = new QueryWrapper<>();
        queryMenu.ne("Url", "#");
        List<Menu> menus = _dao.selectList(queryMenu);
        List<SimpleTreeModel> simpleTreeModels = new ArrayList<>();
        menus.stream().filter(t -> t.getIsMenu().equals(true)).forEach(t -> {
            SimpleTreeModel rootNode = new SimpleTreeModel();
            rootNode.setLabel(t.getMenuName());
            rootNode.setValue(t.getMenuId());
            rootNode.setParentId(t.getParentId());
            rootNode.setChildren(CreateActionChildren(menus, rootNode));
            simpleTreeModels.add(rootNode);
        });
        return simpleTreeModels;
    }

    public static List<SimpleTreeModel> CreateActionChildren(List<Menu> allActionList, SimpleTreeModel dto) {
        List<SimpleTreeModel> simpleTreeModels = new ArrayList<>();
        List<Menu> permissions = allActionList.stream()
                .filter(t -> t.getParentId().equals(dto.getValue()))
                .filter(t -> t.getIsMenu().equals(false)).collect(Collectors.toList());
        permissions.forEach(t -> {
            SimpleTreeModel simpleTreeModel = new SimpleTreeModel();
            simpleTreeModel.setValue(t.getMenuId());
            simpleTreeModel.setParentId(t.getParentId());
            simpleTreeModel.setLabel(t.getMenuName());
            simpleTreeModels.add(simpleTreeModel);
        });
        return simpleTreeModels;
    }

}
