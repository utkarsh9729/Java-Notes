package org.interview.java.core.lld.solid;

public class OpenClosedPrinciple {
//    OCP states: Software entities (classes, modules, functions) should be open for extension, but closed for modification.

    public static void main(String[] args) {

        PaymentProcessor paymentProcessor = new CreditCardPaymentService();
        PaymentProcessor paymentProcessor1 = new UPIPaymentService();
        paymentProcessor.processPayment(1000);
        paymentProcessor1.processPayment(2000);
    }

    
}
