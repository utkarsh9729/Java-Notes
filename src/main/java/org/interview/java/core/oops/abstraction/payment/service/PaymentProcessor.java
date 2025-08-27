package org.interview.java.core.oops.abstraction.payment.service;

import org.interview.java.core.oops.abstraction.payment.Payment;
import org.interview.java.core.oops.abstraction.payment.Refundable;

import java.sql.Ref;

public class PaymentProcessor {

    public static void main(String[] args) {


        CreditCardPaymentService creditCardPaymentService = new CreditCardPaymentService("CC123",100.0,"Credit Card",true);
        creditCardPaymentService.pay();

        UpiPaymentService upiPaymentService = new UpiPaymentService("UPI341",150.0,"UPI",true);
        upiPaymentService.pay();
        CreditCardPaymentService creditCardPaymentService1 = new CreditCardPaymentService("CC123513",100.0,"Credit Card",false);
        creditCardPaymentService1.pay();
        Refundable.printRefundRecept("CC123513");
        System.out.println(Refundable.abc);
    }
}
