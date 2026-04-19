package org.interview.java.core.collections.list;

import java.util.NoSuchElementException;

/**
 * A custom Singly Linked List implementation for interview demonstration.
 * Covers: Insertion at end, Deletion from front, and Printing.
 */
public class LinkedList {

    private Node head = null;
    private Node tail = null;

    /**
     * Adds a new node to the end of the list.
     */
    public void add(Node node) {
        if (node == null) return;
        
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        tail.next = null;
    }

    /**
     * Removes the first node (head) from the list.
     */
    public void remove() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
    }

    /**
     * Prints all elements in the linked list.
     */
    public void print() {
        Node temp = head;
        if (temp == null) {
            System.out.println("List is empty");
            return;
        }
        while (temp != null) {
            System.out.print(temp.val + (temp.next != null ? " -> " : ""));
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        System.out.println("Adding elements: 10, 20, 30, 40");
        list.add(new Node(10));
        list.add(new Node(20));
        list.add(new Node(30));
        list.add(new Node(40));
        list.print();

        System.out.println("Removing front element:");
        list.remove();
        list.print();

        System.out.println("Removing two more elements:");
        list.remove();
        list.remove();
        list.print();

        System.out.println("Adding element: 50");
        list.add(new Node(50));
        list.print();
    }
}
