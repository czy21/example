package com.team.application;

import lombok.Data;
import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class BasicTest {

    static class TestEntity {
        private String name;
    }


    @Test
    public void tOne() {

        TestEntity t = new TestEntity();
        t.name = "nishishei";

        System.out.println("a");

        Integer age = 12;
        change(age);

        System.out.println("b");

    }

    public void change(Integer age) {
        age = 25;
    }

    @Data
    static class ListNode {
        String value;
        ListNode next;
    }

    @Test
    public void Two() {
        List<TreeNode> tuples = new ArrayList<>();
        TreeNode tuple1 = new TreeNode("a", "b");
        TreeNode tuple2 = new TreeNode("m", "n");
//        TreeNode tuple3 = new TreeNode("c", "d");
//        TreeNode tuple4 = new TreeNode("m", "n");
//        TreeNode tuple5 = new TreeNode("m", "o");

        tuples.add(tuple1);
        tuples.add(tuple2);
//        tuples.add(tuple3);
//        tuples.add(tuple4);
//        tuples.add(tuple5);

//        List<DoubleLinkedList<TreeNode>> nodes = getTree(tuples);

//        isCircle(tuples);

        tuples.forEach(s -> {
            var pp = isCircle(s.getInstitutionId(), tuples);
            System.out.println("sss");
        });

        System.out.println("aa");
    }

    private static MutablePair<Boolean, Set<TreeNode>> isCircle(String id, Collection<TreeNode> resRelationList) {
        boolean isCircle = false;
        /* 队列*/
        List<String> temp = new ArrayList<>();
        temp.add(id);
        LinkedList<String> queue = new LinkedList<>(temp);
        List<TreeNode> cirCle = new ArrayList<>();
        /* 标记集合*/
        Set<TreeNode> color = new HashSet<>();
        while (queue.size() > 0) {
            String parentId = queue.poll();
            for (TreeNode i : resRelationList) {
                /* 找到节点的对应的关系，也就是边*/
                if (parentId.equals(i.getInstitutionId())) {
                    /* 查看边是否被访问过*/
                    if (!color.contains(i)) {
                        color.add(i);
                        if (!queue.contains(i.getInstitutionIdAim())) {
                            /*加入队列 */
                            queue.add(i.getInstitutionIdAim());
                        }
                    } else {
                        /* 如果重复访问，则有环*/
                        isCircle = true;
                        queue.clear();
                        break;
                    }
                }
            }
        }
        return new MutablePair<>(isCircle, color);
    }

    //
    public List<DoubleLinkedList<TreeNode>> getTree(List<TreeNode> items) {
        List<DoubleLinkedList<TreeNode>> nodes = new ArrayList<>();
        List<TreeNode> roots = items
                .stream()
                .filter(s -> {
                    return items.stream().noneMatch(t -> t.getInstitutionIdAim().equals(s.getInstitutionId())) | items.stream().anyMatch(t -> t.getInstitutionIdAim().equals(s.getInstitutionId()));
                }).collect(Collectors.toList());
        for (TreeNode s : roots) {
            DoubleLinkedList<TreeNode> single = new DoubleLinkedList<>();
            single.addHead(new DoubleNode<>(s));
            buildLinkItem(items, s, single);
            nodes.add(single);
        }
        return nodes;
    }

    private void buildLinkItem(List<TreeNode> items, TreeNode item, DoubleLinkedList<TreeNode> node) {
        items.forEach(r -> {
            if (r.getInstitutionId().equals(item.getInstitutionIdAim())) {
                node.addLast(new DoubleNode<>(r));
                buildLinkItem(items, r, node);
            }
        });
    }

}
