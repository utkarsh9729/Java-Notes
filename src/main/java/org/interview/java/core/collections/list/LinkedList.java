//package org.interview.java.core.collections.list;
//
//
//// Create a Custom Linked List
//// Insertion at the End
//// Deletion From Front
//// Print Linked List method
//
//import java.util.NoSuchElementException;
//
//public class LinkedList {
//
//
//   public static Node head = null;
//    public static Node end = null;
//
//    public static void add(Node node){
//
//      if(head == null && end == null){
//          head = node;
//          end = node;
//          end.next = null;
//      }
//      else{
//          end.next = node;
//          end = node;
//          end.next =null;
//      }
//    }
//
//    public static void remove() {
//        if (head == null) {
//            throw new NoSuchElementException("List is empty");
//        }
//        head = head.next;
//        if (head == null) end = null;
//    }
//
//    public static void print(){
//
//        Node temp = head;
//        while(temp!=null){
//
//            System.out.println(temp.val);
//            temp = temp.next;
//        }
//
//    }
//    public static void main(String[] args) {
//
//
//        Node node = new Node(10);
//        add(node);
////        print();
//        add(new Node(20));
//        add(new Node(30));
//        add(new Node(40));
////        print();
//        remove();
////        print();
//
//        remove();
//
//        remove();
//
//        print();
//        add(new Node(20));
//        add(new Node(30));
//        add(new Node(40));
//        print();
//
//    }
//
//}
