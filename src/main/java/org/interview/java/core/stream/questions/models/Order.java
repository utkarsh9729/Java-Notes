package org.interview.java.core.stream.questions.models;

import javax.sound.sampled.Line;
import java.util.List;

public class Order {
//orderId, customerEmail, List<LineItem> items, orderDate, status (PENDING, DELIVERED, CANCELLED).

    private long orderId;

    private String customerEmail;
    private List<LineItem> items;

    private String orderDate;

    private Status status;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Order(long orderId, String customerEmail, List<LineItem> items, String orderDate, Status status) {
        this.orderId = orderId;
        this.customerEmail = customerEmail;
        this.items = items;
        this.orderDate = orderDate;
        this.status = status;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public void setItems(List<LineItem> items) {
        this.items = items;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
