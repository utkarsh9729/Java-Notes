package org.interview.java.core.lld.designpatterns.strategy;


public class PaymentMain {
    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();
        paymentService.setStrategy(new PaymentOperation());
        System.out.println(paymentService.executePayment(199.9,"123"));
        System.out.println(paymentService.executeRefund("123"));

    }
}
