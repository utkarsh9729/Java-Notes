package org.interview.java.core.lld.designpatterns.factory;

public class PaymentProcessor {


    public static void main(String[] args) {


        Payment payment  = PaymentFactory.getPayment("upi");
        System.out.println(payment.pay());
    }
}
