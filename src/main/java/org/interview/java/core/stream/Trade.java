package org.interview.java.core.stream;

public class Trade {

    private String ccy;

    private double amount;

    public Trade(String ccy, double amount) {
        this.ccy = ccy;
        this.amount = amount;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
