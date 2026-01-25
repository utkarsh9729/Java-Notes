package org.interview.java.core.stream.questions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
* . The Cartesian Product (flatMap) Given two lists of integers: List<Integer> listA = List.of(1, 2, 3) and List<Integer> listB = List.of(4, 5).

Task: Use flatMap to generate all possible pairs [i, j] where i is from listA and j is from listB.

Condition: Filter out pairs where the sum of i + j is even.

Output: List<int[]> or List<String> representing the pairs (e.g., ["1,4", "2,5", ...]).
* */
public class CartesianProductFlatmap {
    public static void main(String[] args) {
        List<Integer> listA = Arrays.asList(1,2,3);
        List<Integer> listB = Arrays.asList(4,6);
        List<String> ans  = listA.stream().flatMap(a -> listB.stream().filter(b -> (a+b)%2!=0).map(b -> a+", "+b)).collect(Collectors.toList());
        ans.forEach(System.out::println);
    }
}
