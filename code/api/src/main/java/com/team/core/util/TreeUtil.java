package com.team.core.util;


import com.team.entity.vo.MenuDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    @Data
    public static class Node {
        private String MenuId;
        private String ParentId;
        private String MenuName;
        private String Icon;
        private String Url;
        private List<Node> children;
    }

    List<TreeUtil.Node> nodes;

    public TreeUtil(List<Node> nodes) {
        this.nodes = nodes;
    }
    /**
     * 构建树形结构
     *
     * @return
     */

    public List<Node> buildTree() {
        List<Node> treeNodes = new ArrayList<>();
        List<Node> rootNodes = getRootNodes();
        for (Node rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    /**
     * 递归子节点
     *
     * @param node
     */

    public void buildChildNodes(Node node) {

        List<Node> children = getChildNodes(node);

        if (!children.isEmpty()) {

            for (Node child : children) {

                buildChildNodes(child);

            }

            node.setChildren(children);

        }

    }

    /**
     * 获取父节点下所有的子节点
     *
     * @return
     */

    public List<Node> getChildNodes(Node pnode) {

        List<Node> childNodes = new ArrayList<Node>();

        for (Node n : nodes) {

            if (pnode.getMenuId().equals(n.getParentId())) {
                childNodes.add(n);
            }

        }

        return childNodes;

    }

    /**
     * 判断是否为根节点
     *
     * @return
     */

    public boolean rootNode(Node node) {

        boolean isRootNode = true;
        for (Node n : nodes) {
            if (node.getParentId().equals(n.getMenuId())) {
                isRootNode = false;
                break;
            }
        }
        return isRootNode;
    }

    /**
     * 获取集合中所有的根节点
     *
     * @return
     */

    public List<Node> getRootNodes() {
        List<Node> rootNodes = new ArrayList<>();
        for (Node n : nodes) {
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }
}
