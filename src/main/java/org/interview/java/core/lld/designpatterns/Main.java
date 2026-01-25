package org.interview.java.core.lld.designpatterns;

public class Main {

    public static void main(String[] args) {
        Object instance1 = Singleton.getInstance();
        Object instance2 = Singleton.getInstance();

        System.out.println("Are both instances the same? " + (instance1 == instance2));
    }

}
