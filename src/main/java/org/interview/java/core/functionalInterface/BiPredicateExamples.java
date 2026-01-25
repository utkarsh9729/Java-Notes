package org.interview.java.core.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class BiPredicateExamples {
    public static void main(String[] args) {

        List<String> li  = Arrays.asList("abc","xyz","abcd","xyza", "pqrsuvwxyz");

        BiPredicate<String,Integer> lengthFilter = (str,len)-> str.length() == len;

        li.stream().filter((str)-> lengthFilter.test(str,4)).forEach(System.out::println);
    }


}
