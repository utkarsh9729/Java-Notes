package org.interview.java.core.multithreading;

import java.util.Arrays;

public class MyThread extends  Thread{

    @Override
    public void run(){

        System.out.println("Run Method Execution");

        Arrays.asList(1,2,3,4,4,5,6,7).stream().forEach(System.out::println);
    }
}
