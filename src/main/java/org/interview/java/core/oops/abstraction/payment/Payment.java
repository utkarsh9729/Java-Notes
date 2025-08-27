package org.interview.java.core.oops.abstraction.payment;

public abstract  class Payment {


    private String id;

    private Double amount;

    private String mode;

    private boolean status;


    protected abstract void pay();

    private void printReceipt(){

        String printFormat = " ID : %s  Amound : %f Mode: %s Status: %b ";
        System.out.printf((printFormat) + "%n",id,amount,mode,status);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Payment(String id, Double amount, String mode, boolean status) {
        this.id = id;
        this.amount = amount;
        this.mode = mode;
        this.status = status;
    }


}
