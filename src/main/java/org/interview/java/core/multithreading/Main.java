package org.interview.java.core.multithreading;

public class Main {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());

        MyThread thread = new MyThread();
//        thread.start();

        Thread thread1 = new Thread(new ThredUsingInterface() );

        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("My Name is Utkarsh");
            }
        });

        Thread t3 = new Thread(()->{
            System.out.println("ABCD");
        });
        t3.start();
    }
}
