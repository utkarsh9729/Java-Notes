package org.interview.java.core.lld.designpatterns.factory;

public class CreditCardPayment implements  Payment{
    @Override
    public String pay() {
        return "Success";
    }
}
