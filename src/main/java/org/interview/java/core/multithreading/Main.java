package org.interview.java.core.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main class to demonstrate various ways to create and manage threads in Java.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Main thread: " + Thread.currentThread().getName());

        // 1. Extending Thread class
        MyThread t1 = new MyThread();
        t1.setName("ExtendingThread");
        t1.start();

        // 2. Implementing Runnable interface
        Thread t2 = new Thread(new ThreadUsingRunnable());
        t2.setName("RunnableThread");
        t2.start();

        // 3. Anonymous Inner Class
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Inner Class thread: " + Thread.currentThread().getName());
            }
        });
        t3.start();

        // 4. Lambda Expression (Modern Java)
        Thread t4 = new Thread(() -> {
            System.out.println("Lambda thread: " + Thread.currentThread().getName());
        });
        t4.start();

        // 5. Using ExecutorService (Best Practice for modern applications)
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> System.out.println("ExecutorService task 1: " + Thread.currentThread().getName()));
        executor.submit(() -> System.out.println("ExecutorService task 2: " + Thread.currentThread().getName()));
        
        // Always shut down the executor
        executor.shutdown();
    }
}
