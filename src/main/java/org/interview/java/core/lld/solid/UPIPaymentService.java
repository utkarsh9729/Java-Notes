package org.interview.java.core.lld.solid;

public class UPIPaymentService implements PaymentProcessor{
    @Override
    public void processPayment(double amount) {
            System.out.println("Processing UPI payment of amount: " + amount);
    }
}
