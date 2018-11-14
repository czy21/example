package com.team.core.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.team.core.extension.StringExtension;
import lombok.Data;
import org.mapstruct.Mapper;

import java.util.*;

public class TreeUtil {

    @Data
    public static class Node {
        private String MenuId;
        private String ParentId;
        private String MenuName;
        private String Icon;
        private String Url;
        private List<Node> children = new ArrayList<>();
    }

    public static List<Node> createTreeMenus(List<Node> menus) {
        Node root = new Node();
        root.setParentId(StringExtension.GuidEmpty);
        Map<String, Node> dataMap = new HashMap<>();
        for (Node menu : menus) {
            dataMap.put(menu.getMenuId(), menu);
        }
        Set<Map.Entry<String, Node>> entrySet = dataMap.entrySet();
        for (Map.Entry<String, Node> entry : entrySet) {
            Node menu = entry.getValue();
            if (StringExtension.GuidIsNullOrEmpty(menu.getParentId())) {
                root.getChildren().add(menu);
            } else {
                dataMap.get(menu.getParentId()).getChildren().add(menu);
            }
        }
        return root.getChildren();
    }
}
