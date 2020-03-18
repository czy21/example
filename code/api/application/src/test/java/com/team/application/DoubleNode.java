package com.team.application;

public class DoubleNode<T> {

    private T data;
    private DoubleNode<T> prep;
    private DoubleNode<T> next;


    public DoubleNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DoubleNode<T> getPrep() {
        return prep;
    }

    public void setPrep(DoubleNode<T> prep) {
        this.prep = prep;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
}