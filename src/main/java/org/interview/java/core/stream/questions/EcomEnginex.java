package org.interview.java.core.stream.questions;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.interview.java.core.stream.questions.models.Category;
import org.interview.java.core.stream.questions.models.LineItem;
import org.interview.java.core.stream.questions.models.Order;
import org.interview.java.core.stream.questions.models.Status;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

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
*  */
public class EcomEnginex {

    public static void main(String[] args) {

        List<LineItem> itemList = Arrays.asList(new LineItem(1, Category.ELECTRONICS,5,10),
                new LineItem(2, Category.FASHION,15,110),new LineItem(1, Category.HOME,12,50),
        new LineItem(1, Category.ELECTRONICS,1,111));
        List<LineItem> itemList1 = Arrays.asList(new LineItem(1, Category.ELECTRONICS,1,10),
                new LineItem(2, Category.FASHION,2,110),new LineItem(1, Category.HOME,3,50),
                new LineItem(1, Category.ELECTRONICS,4,111));

        Order firstOrder = new Order(1,"abc@gmail.com",itemList,"12-01-2026", Status.DELIVERED);
        Order order2 = new Order(1,"abc1@gmail.com",itemList1,"11-01-2026", Status.PENDING);
        Order order3 = new Order(1,"abc@gmail.com", Collections.singletonList(new LineItem(1, Category.ELECTRONICS, 4, 111)),"10-01-2026", Status.CANCELLED);

        List<Order> orders = Arrays.asList(firstOrder,order2,order3);

// Q1 =>Category Revenue: Calculate the total revenue generated for the "ELECTRONICS" category across all DELIVERED orders. (Hint: You will need to flatten the line items).

        Optional<Double> ans =  orders.stream().filter(
                e-> e.getStatus().equals(Status.DELIVERED)).flatMap(e ->
                e.getItems().stream().
                        filter(i -> i.getCategory().equals(Category.ELECTRONICS)).
                        mapToDouble(x -> x.getQuantity()*x.getUnitPrice()).boxed()).reduce(Double::sum);

        ans.ifPresent(System.out::println);

//        Top Spender: Find the customerEmail of the user who has spent the most money in total across all their orders.

        String topSpenderEmail = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerEmail,
                        Collectors.summingDouble(o -> o.getItems().stream()
                                .mapToDouble(i -> i.getQuantity() * i.getUnitPrice())
                                .sum())
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No Data");

//        Product Frequency: Return a Map<String, Long> where the key is productId and the value is the total quantity sold. Sort this map by value (descending) and extract the top 5 distinct products.

        Map<Long, Long> topProducts = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(
                        LineItem::getProductId,
                        Collectors.summingLong(LineItem::getQuantity)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));    }
}

