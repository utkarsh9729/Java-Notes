package org.interview.java.core.oops.inheritance.vehicle;

public class Consumer {

    public static void main(String[] args) {

        Car car = new Car();

        Bike bike = new Bike();

        car.start();
        car.drive();
        car.stop();
        car.openBoot();

        bike.start();

        bike.setStandOff(true);
        bike.start();
        bike.stop();
    }
}
