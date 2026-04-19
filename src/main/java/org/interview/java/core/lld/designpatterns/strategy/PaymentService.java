package org.interview.java.core.lld.designpatterns.strategy;



public class PaymentService {

    private PaymentStrategy strategy;

    // We inject the strategy we want to use
    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public String executePayment(double amount, String id) {
        return strategy.pay(amount, id);
    }

    public String executeRefund(String id) {
        return strategy.refund(id);
    }
}
