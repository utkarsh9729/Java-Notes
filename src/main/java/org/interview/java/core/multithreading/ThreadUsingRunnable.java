package org.interview.java.core.multithreading;

import java.util.Arrays;

/**
 * Demonstrates creating a thread by implementing the Runnable interface.
 * This is preferred over extending the Thread class because it allows 
 * the class to extend another class if needed.
 */
public class ThreadUsingRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread started using Runnable: " + Thread.currentThread().getName());
        
        Arrays.asList(1, 2, 3, 4, 5, 6, 7).stream()
              .forEach(num -> System.out.println("Processing: " + num));
        
        System.out.println("Thread execution finished: " + Thread.currentThread().getName());
    }
}
