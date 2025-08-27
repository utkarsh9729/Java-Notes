package org.interview.java.core.oops.abstraction.payment.service;

import org.interview.java.core.oops.abstraction.payment.Payment;

public class UpiPaymentService extends Payment {
    public UpiPaymentService(String id, Double amount, String mode, boolean status) {
        super(id, amount, mode, status);
    }


    @Override
    protected void pay() {
        System.out.println("A UPI Payment is done");
    }
}
