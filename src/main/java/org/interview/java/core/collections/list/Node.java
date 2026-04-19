package org.interview.java.core.collections.list;


public class Node {

    public int val;
    public Node next;
    public Node prev;
    public int key;

    public Node(int key,int val) {
        this.val = val;
        this.key = key;
    }

    public Node(int val) {
        this.val = val;
    }
}