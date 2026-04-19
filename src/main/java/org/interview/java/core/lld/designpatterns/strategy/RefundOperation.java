package org.interview.java.core.lld.designpatterns.strategy;

public class RefundOperation implements PaymentStrategy {

    @Override
    public String pay(double amount, String id) {
        return "Paid " + amount + " via Credit Card (ID: " + id + ")";
    }

    @Override
    public String refund(String id) {
        return "Refunded to Credit Card (ID: " + id + ")";
    }
}
