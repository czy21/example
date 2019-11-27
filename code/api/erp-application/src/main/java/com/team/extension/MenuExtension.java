package com.team.extension;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.team.entity.mybatis.system.Menu;
import com.team.model.SimpleItemModel;
import com.team.mapper.MenuRepository;
import com.team.util.TreeUtil;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MenuExtension {
    @Data
    public static class Node {
        private Long menuId;
        private Long parentId;
        private String menuName;
        private String icon;
        private String url;
        private Integer sort;
        private List<Node> children = new ArrayList<>();

        public void sortChildren() {
            children.sort((menu1, menu2) -> {
                int result = 0;
                Integer ordby1 = menu1.getSort();
                Integer ordby2 = menu2.getSort();

                if (null != ordby1 && null != ordby2) {
                    result = (ordby1 < ordby2 ? -1 : (ordby1.equals(ordby2) ? 0 : 1));
                }
                return result;
            });
            for (Node child : children) {
                child.sortChildren();
            }
        }
    }

    private static MenuRepository _dao;

    public MenuExtension(MenuRepository dao) {
        _dao = dao;
    }

    public static List<SimpleItemModel> convertToSimple() {
        List<SimpleItemModel> simples = new ArrayList<>();
        _dao.selectList(null).forEach((t) -> {
            SimpleItemModel temp = new SimpleItemModel();
            temp.setValue(t.getMenuId());
            temp.setLabel(t.getMenuName());
            temp.setParentId(t.getParentId());
            simples.add(temp);
        });
        return simples;
    }

    public static JSONArray convertToTreeSimple() {
        return TreeUtil.listToTree(JSONArray.parseArray(JSON.toJSONString(convertToSimple())), "value", "parentId", "children");
    }

    public static List<Node> createTreeMenus(List<Node> menus) {
        Node root = new Node();
        root.setParentId(0L);
        Map<Long, Node> dataMap = new HashMap<>();
        menus.forEach(t -> dataMap.put(t.getMenuId(), t));
        Set<Map.Entry<Long, Node>> entrySet = dataMap.entrySet();
        entrySet.forEach(t -> {
            Node menu = t.getValue();
            if (menu.getParentId().equals(0L)) {
                root.getChildren().add(menu);
            } else {
                dataMap.get(menu.getParentId()).getChildren().add(menu);
            }
        });
        root.sortChildren();
        return root.getChildren();
    }

    public static List<Menu> getSons(List<Menu> list, Long parentId) {
        if (StringUtils.isEmpty(parentId)) {
            return list.stream()
                    .sorted(Comparator.comparing(Menu::getSort, Comparator.nullsLast(Integer::compareTo)))
                    .collect(Collectors.toList());
        }
        List<Menu> query = list.stream().filter(t -> t.getMenuId().equals(parentId)).collect(Collectors.toList());
        query.addAll(getSonList(list, parentId));
        return query;
    }

    private static List<Menu> getSonList(List<Menu> list, Long parentId) {
        List<Menu> menus = new ArrayList<>();
        list.forEach(t -> {
            if (t.getParentId().equals(parentId)) {
                menus.addAll(getSons(list, t.getMenuId()));
            }
        });
        return menus.stream().sorted(Comparator.comparing(Menu::getSort, Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());
    }

}
