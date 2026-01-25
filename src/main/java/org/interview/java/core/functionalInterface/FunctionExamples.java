package org.interview.java.core.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionExamples {

    public static void main(String[] args) {

        List<Integer> integerList = Arrays.asList(1,3,4,5,6,7,8,9);

        Function<Integer,String> converter = String::valueOf;

        integerList.stream().map(converter).forEach(System.out::println);

    }

}
