package org.interview.java.core.lld.designpatterns.factory;

public class UpiPayment implements  Payment{
    @Override
    public String pay() {
        return "Success";
    }
}
