package org.interview.java.core.lld.solid;

public class CreditCardPaymentService implements  PaymentProcessor{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of amount: " + amount);
    }
}
