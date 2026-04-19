package org.interview.java.core.stream.questions.models;

public class LineItem {
    /*productId, category (ELECTRONICS, FASHION, HOME), quantity, unitPrice.*/

    private long productId;

    private Category category;

    private long quantity;

    private double unitPrice;

    public LineItem(long productId, Category category, long quantity, double unitPrice) {
        this.productId = productId;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
