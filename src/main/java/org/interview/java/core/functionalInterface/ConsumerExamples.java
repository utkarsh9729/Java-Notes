package org.interview.java.core.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExamples {
    public static void main(String[] args) {

        List<Integer> li = Arrays.asList(1,2,3,4,5,5,6,7,7,8,8,9,9,9,9,9,10);

        Consumer<Integer> printer = System.out::println;

        li.forEach(printer);


        // Example 2 ->
//         This can be used with the notification service to send the notification on the successful transaction

    }
}
