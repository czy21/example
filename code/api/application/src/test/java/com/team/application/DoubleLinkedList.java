package com.team.application;

public class DoubleLinkedList<T> {

    /**
     * 头结点
     */
    private DoubleNode<T> head;
    /**
     * 尾结点
     */
    private DoubleNode<T> last;

    public DoubleLinkedList() {
        head = null;
        last = null;
    }

    /**
     * 头部插入数据
     */
    public void addHead(DoubleNode<T> node) {
        if (head == null) {
            last = node;
        } else {
            head.setPrep(node);
            node.setNext(head);
        }
        // 插入的节点作为头结点
        head = node;
    }

    /**
     * 尾部插入数据
     */
    public void addLast(DoubleNode<T> node) {
        if (head == null) {
            head = node;
        } else {
            last.setNext(node);
            node.setPrep(last);
        }
        // 最后的节点设置为最新的节点
        last = node;
    }

    /**
     * 删除头结点
     */
    public DoubleNode<T> removeHead() {
        if (head == null) {
            throw new RuntimeException("链表数据不存在");
        }
        DoubleNode<T> temp = head;
        if (head.getNext() == null) {
            last = null;
        } else {
            head.getNext().setPrep(null);
        }
        head = temp.getNext();
        return temp;
    }

    /**
     * 删除尾结点
     */
    public DoubleNode<T> removeLast() {
        if (head == null) {
            throw new RuntimeException("链表数据不存在");
        }
        DoubleNode<T> temp = last;
        if (head.getNext() == null) {
            head = null;
            last = null;
        } else {
            last.getPrep().setNext(null);
        }
        last = temp.getPrep();
        return temp;
    }

    /**
     * 删除指定的结点
     */
    public DoubleNode<T> removeNode(DoubleNode<T> node) {
        if (head == null) {
            throw new RuntimeException("链表数据不存在");
        }
        DoubleNode<T> current = head;
        while (current.getData() != node.getData()) {
            if (current.getNext() == null) {
                System.out.println("没有找到该节点");
                return null;
            }
            current = current.getNext();
        }
        if (current == head) {
            return removeHead();
        } else {
            current.getPrep().setNext(current.getNext());
            current.getNext().setPrep(current.getPrep());
        }
        return current;
    }

    /**
     * 打印链表
     */
    public void printDoubleLinkList() {
        if (head == null) {
            System.out.println("双向链表数据不存在");
        }
        DoubleNode<T> node = head;
        StringBuilder stringBuilder = new StringBuilder();
        while (node != null) {
            stringBuilder.append(node.getData()).append(",");
            node = node.getNext();
        }
        System.out.println(stringBuilder.toString());
        System.out.println("******************");
    }

}
