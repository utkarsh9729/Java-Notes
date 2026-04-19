package org.interview.java.core.lld.designpatterns.strategy;

public interface PaymentStrategy {

    String pay(double amount, String id);
    String refund(String id);}
