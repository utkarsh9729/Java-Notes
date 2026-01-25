package org.interview.java.core.collections.questions;


import org.interview.java.core.collections.list.Node;

import java.util.HashMap;

import static java.util.Objects.nonNull;

public class CustomLRUCache {

    private HashMap<Integer, Node> map;
    private int capacity;
    private Node head;
    private Node tail;
    public CustomLRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        delete(map.get(key));
        insertAtTheTop(map.get(key));
        return map.get(key).val;

    }

    private void delete(Node node){
        assert  nonNull(node);
        node.prev = node.next;
    }

    private  void insertAtTheTop(Node node){
        assert nonNull(node);
        Node tempNode = head.next;
        head.next = node;
        node.next = tempNode;
        node.prev = head;
        tempNode.prev = node;
    }

    public void put(int key, int value){
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            delete(node);
            insertAtTheTop(node);

        }
        else{
            if(map.size()==capacity){
                Node lastNode = tail.prev;
                delete(lastNode);
            }
            Node node = new Node(key,value);
            insertAtTheTop(node);
            map.put(key,node);
        }
    }
}
