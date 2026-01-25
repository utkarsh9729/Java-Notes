package org.interview.java.core.stream.questions;

/*
*
* Scenario A: The E-Commerce Analytics Engine
You have a data structure of Orders.

Order contains: orderId, customerEmail, List<LineItem> items, orderDate, status (PENDING, DELIVERED, CANCELLED).

LineItem contains: productId, category (ELECTRONICS, FASHION, HOME), quantity, unitPrice.

Perform the following queries using a single stream pipeline for each:

Category Revenue: Calculate the total revenue generated for the "ELECTRONICS" category across all DELIVERED orders. (Hint: You will need to flatten the line items).

Top Spender: Find the customerEmail of the user who has spent the most money in total across all their orders.

Product Frequency: Return a Map<String, Long> where the key is productId and the value is the total quantity sold. Sort this map by value (descending) and extract the top 5 distinct products.
*
* */
public class EcomEnginex {
}
