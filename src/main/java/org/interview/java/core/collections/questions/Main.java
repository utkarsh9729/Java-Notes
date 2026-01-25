package org.interview.java.core.collections.questions;

 class Main {
    public static void main(String[] args) {

        CustomLRUCache cache = new CustomLRUCache(4);



        cache.put(4,4);
        cache.put(2,7);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));

        cache.put(4,5);
        cache.put(5,5);
        cache.put(6,6);
        cache.put(7,6);

        System.out.println(cache.get(6));

    }
}
