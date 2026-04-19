package org.interview.java.core.lld.designpatterns.strategy;

public class RefundModel {

    private String paymentId;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public RefundModel(String paymentId) {
        this.paymentId = paymentId;
    }
}
