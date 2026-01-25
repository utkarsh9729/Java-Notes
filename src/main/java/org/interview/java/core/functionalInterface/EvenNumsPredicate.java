package org.interview.java.core.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class EvenNumsPredicate {

    public static void main(String[] args) {

        List<Integer> li = Arrays.asList(1,2,3,4,5,5,6,7,7,8,8,9,9,9,9,9,10);

        Predicate<Integer> evenFilter = (num)-> num%2==0;

        li.stream().filter(evenFilter).forEach(System.out::println);

//         Chaining

        Predicate<Integer> greaterThan5 = (num) -> num>5;

        li.stream().filter(evenFilter.and(greaterThan5)).forEach(System.out::println);
    }
}
