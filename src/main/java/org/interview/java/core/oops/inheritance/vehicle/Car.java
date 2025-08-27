package org.interview.java.core.oops.inheritance.vehicle;

public class Car extends Vehicle {


    public Car (){

    }

    public void openBoot(){


        System.out.println("Opening The boot");
    }

    public void closeBoot(){

        System.out.println("Closing the boot");
    }

    public void openOrvm(){
        System.out.println("ORVM Opened");
    }
    @Override
    public void drive(){
        openOrvm();
        System.out.println("Driving the car now");
    }

}
