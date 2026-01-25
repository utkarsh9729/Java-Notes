package org.interview.java.core.multithreading.synchronization;

public class CounterThread  implements   Runnable{

    private Counter counter;

    public CounterThread(Counter counter){
        this.counter = counter;
    }
    @Override
    public void run(){

        for(int i=0;i<1000;i++) counter.increment();
    }
}
