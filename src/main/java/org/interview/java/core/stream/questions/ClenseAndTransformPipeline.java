package org.interview.java.core.stream.questions;



import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
* The "Cleanse and Transform" Pipeline Given a list of Strings: [" apple ", "banana", " ", "pear", "MELON "].

Create a Predicate<String> that checks if a string is not blank.

Create a Function<String, String> that trims the string and converts it to uppercase.

Task: Use a stream to process the list, applying the predicate and function, and collect the results into a LinkedList (not a generic List).
*
* */
public class ClenseAndTransformPipeline {

    public static void main(String[] args) {
        List<String> pipeline = Arrays.asList("apple", "banana", "pear", "MELON",""," ","                       ");

        Predicate<String> isBlack = (str) -> !str.trim().isEmpty();
        Function<String, String> upperCaseCaster = (str) -> str.trim().toUpperCase();

        LinkedList<String> li =pipeline.stream().filter(isBlack).map(upperCaseCaster).collect(Collectors.toCollection(LinkedList::new));
        li.forEach(System.out::println);
    }

}
