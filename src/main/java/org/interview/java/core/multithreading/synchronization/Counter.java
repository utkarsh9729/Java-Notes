package org.interview.java.core.multithreading.synchronization;

public class Counter {

    private int count = 0;


//     Mutual Exclusion
    public synchronized void increment (){
        count ++;
    }

    public int getCount() {
        return count;
    }
}
