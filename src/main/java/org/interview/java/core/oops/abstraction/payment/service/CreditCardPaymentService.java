package org.interview.java.core.oops.abstraction.payment.service;

import org.interview.java.core.oops.abstraction.payment.Payment;
import org.interview.java.core.oops.abstraction.payment.Refundable;

public class CreditCardPaymentService  extends Payment implements Refundable {
    public CreditCardPaymentService(String id, Double amount, String mode, boolean status) {
        super(id, amount, mode, status);
    }



    @Override
    protected void pay() {
        if(isStatus())
        System.out.println(" A Credit Card Payment was done");
        else{
            refund();
        }
    }

    @Override
    public void refund() {
        System.out.println("Refund Initiated for Payment : "+this.getId());
    }
}
