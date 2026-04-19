package org.interview.java.core.stream;


import java.util.*;
import java.util.stream.Collectors;

/*
*
* Q1. Filter Odd Numbers

Given a list of integers, filter out only the odd numbers using Streams.

Q2. Find Unique Elements

Given a list of strings with duplicates, return a list of distinct elements using Streams.

Q3. Square and Collect

Given a list of integers, square each number and collect the results into a List.

Q4. Find Maximum Salary

Given a list of employees, find the employee with the highest salary using Streams.

Q5. Convert List to Map

Convert a list of Employee(id, name) into a Map<Integer, String> using Streams.

Q6. Count Word Frequency

Given a string sentence, count the frequency of each word using groupingBy() and counting().

Q7. `Partition Numbers into Prime and Non-Prime`

Given a list of integers, partition them into prime and non-prime numbers using partitioningBy().

Q8. Department-wise Average Salary

Given a list of employees with department and salary, find the average salary per department using Streams.

Q9. Find the First Non-Repeated Character

From a string, find the first non-repeated character using Streams.

Q10. Top 3 Highest Salaries

From a list of employees, find the top 3 employees with the highest salaries using Streams.
*
* */
public class StreamQues {


    public static void main(String[] args) {

//         Q1 odd once out

        List<Integer> list = Arrays.asList(1,3,4,5,6,67,7,8,9,10,11,12,1,2,3,4,5,6,7,8,9);

        list.stream().filter(i -> i%2 !=0).forEach(System.out::println);

        System.out.println(" \n Q2");
//         Q2 -> Unique

        list.stream().collect(Collectors.toSet()).forEach(System.out::println);

//        Q3 Square and collect
        System.out.println(" \n Q3");

        list.stream().map(p-> p*p).forEach(System.out::println);

// Q4
        List<Integer> salary = Arrays.asList(100,200,300,400,500,600,200,300,400,500);

        Optional<Integer> max = salary.stream().max((s1, s2)-> s1-s2);
        max.ifPresent(System.out::println);

// Q5

        Map<Long,List<Integer>> salaries =  salary.stream().collect(Collectors.groupingBy(e-> salary.stream().filter(x-> Objects.equals(x, e)).count()));
        for(Map.Entry<Long,List<Integer>> entry : salaries.entrySet()){
            System.out.println(entry.getKey()+ " " + entry.getValue());
        }

        List<String> arr = Arrays.asList("AbCA","AFhaklsda","Asdfioa","sdajdha","asdikajsd","shdd");

        Map<Integer,List<String>> strList = arr.stream().collect(Collectors.groupingBy(String::length));
        strList.forEach((key, value) -> System.out.println(key + " " + value));


        List<Trade> trades = Arrays.asList(new Trade("EUR",100.0),
                new Trade("EUR",100.0),
        new Trade("USD",400.0),
        new Trade("INR",100.0),
        new Trade("INR",200.0));

        Map<String,Double> result = trades.stream().collect(Collectors.groupingBy(Trade::getCcy, Collectors.summingDouble(Trade::getAmount) ));
    }




}
