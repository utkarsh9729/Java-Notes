package org.interview.java.core.lld.designpatterns.factory;

public class PaymentFactory {
    public static Payment getPayment(String type) {
        if (type == null) return null;

        switch (type.toLowerCase()) {
            case "upi":
                return new UpiPayment();
            case "credit_card":
                return new CreditCardPayment();
            default:
                throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
}
