package org.interview.java.core.lld.designpatterns.strategy;

public class PaymentModel {

    private String paymentId;

    private double amount;

    private String mode;

    public PaymentModel(String paymentId, double amount, String mode) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.mode = mode;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
