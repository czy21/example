package com.team.application;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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
        TreeNode tuple2 = new TreeNode("b", "c");
        TreeNode tuple3 = new TreeNode("c", "d");
        TreeNode tuple4 = new TreeNode("m", "n");
        TreeNode tuple5 = new TreeNode("m", "o");

        tuples.add(tuple1);
        tuples.add(tuple2);
        tuples.add(tuple3);
        tuples.add(tuple4);
        tuples.add(tuple5);

        List<DoubleLinkedList<TreeNode>> nodes = getTree(tuples);


        System.out.println("aa");
    }

    public List<DoubleLinkedList<TreeNode>> getTree(List<TreeNode> items) {
        List<DoubleLinkedList<TreeNode>> nodes = new ArrayList<>();
        List<TreeNode> roots = items
                .stream()
                .filter(s -> items.stream().noneMatch(t -> t.getInstitutionIdAim().equals(s.getInstitutionId())))
                .collect(Collectors.toList());
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
