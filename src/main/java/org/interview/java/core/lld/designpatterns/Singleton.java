package org.interview.java.core.lld.designpatterns;

public class Singleton {

    private volatile static Object mapper;

    private Singleton() {
        // private constructor to prevent instantiation
    }
    public synchronized  static Object getInstance(){
        if(mapper == null) {
            mapper = new Object();
        }
        return mapper;
    }
}
