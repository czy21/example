package com.team.extension;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.team.model.SimpleItemModel;
import com.team.repository.mybatis.system.MenuRepository;
import com.team.util.TreeUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

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
        root.setParentId(LongExtension.empty);
        Map<Long, Node> dataMap = new HashMap<>();
        menus.forEach(t -> dataMap.put(t.getMenuId(), t));
        Set<Map.Entry<Long, Node>> entrySet = dataMap.entrySet();
        entrySet.forEach(t -> {
            Node menu = t.getValue();
            if (LongExtension.guidIsEmpty(menu.getParentId())) {
                root.getChildren().add(menu);
            } else {
                dataMap.get(menu.getParentId()).getChildren().add(menu);
            }
        });
        root.sortChildren();
        return root.getChildren();
    }
}
