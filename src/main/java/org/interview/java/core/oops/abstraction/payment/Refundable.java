package org.interview.java.core.oops.abstraction.payment;

import org.interview.java.core.oops.inheritance.vehicle.Vehicle;

public interface Refundable  {

    public void refund();

    public static void printRefundRecept(String id){
        System.out.println("Refund Success for Id : "+id);
    }

    public  String abc = "123";

    /*In interfaces, all variables are implicitly:

public

static

final*/
}
