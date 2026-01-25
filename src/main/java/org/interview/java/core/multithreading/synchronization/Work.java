package org.interview.java.core.multithreading.synchronization;

public class Work {
    public static void main(String[] args) {

        Counter counter = new Counter();
        Thread counterThread = new Thread( ()-> {
            for(int i=0;i<1000;i++){
                counter.increment();
            }
        });
        Thread counterThread1 =new Thread( new CounterThread(counter));

        counterThread.start();
        counterThread1.start();

        try{
            counterThread.join();
            counterThread1.join();
        }
        catch (Exception e){

        }
        System.out.println(counter.getCount());


    }
}
