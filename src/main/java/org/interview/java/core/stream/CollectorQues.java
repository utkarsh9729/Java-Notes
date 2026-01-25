package org.interview.java.core.stream;


/*
*
* Q1. Convert List to Map

Given a list of Employee(id, name), use Collectors.toMap() to convert it into a Map<Integer, String> where id is key and name is value.
Handle duplicate IDs by keeping the latest entry.

Q2. Joining Names with Delimiter

Given a list of strings, join them into a single string separated by commas, enclosed with [] using Collectors.joining().
Input: ["Alice", "Bob", "Charlie"] → Output: [Alice, Bob, Charlie].

Q3. Word Frequency Counter

Given a sentence, count the frequency of each word using Collectors.groupingBy() and Collectors.counting().

Q4. Department-wise Employee Count

Given a list of employees with department, use groupingBy() to compute the number of employees in each department.

Q5. Partition Numbers into Even and Odd

Given a list of integers, partition them into even and odd using partitioningBy().

Q6. Department-wise Highest Salary

Find the employee with the maximum salary in each department using Collectors.groupingBy() + Collectors.maxBy().

Q7. Collecting into a TreeSet

Given a list of strings, collect them into a TreeSet (sorted order) using Collectors.toCollection().

Q8. Custom Collector – Concatenate Uppercase Strings

Write a custom collector that concatenates all strings in uppercase without using Collectors.joining().

Hint: Implement using Collector.of(supplier, accumulator, combiner, finisher).

Q9. Department-wise Average Salary

Find the average salary per department using Collectors.groupingBy() and averagingInt().

Q10. Custom Collector – Product of Numbers

Write a custom collector to compute the product of all integers in a list.
(It should work like reduce(1, (a, b) -> a * b), but using Collector.of()).
*
* */

public class CollectorQues {
}
