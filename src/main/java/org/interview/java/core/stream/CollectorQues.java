package org.interview.java.core.stream;

import org.interview.java.core.stream.questions.models.Employee;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Solutions for common Collector challenges.
 */
public class CollectorQues {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 60000, "New York", 1),
            new Employee("Bob", "HR", 50000, "London", 2),
            new Employee("Charlie", "IT", 70000, "New York", 3),
            new Employee("David", "HR", 55000, "London", 4),
            new Employee("Eve", "IT", 65000, "New York", 1) // Duplicate ID for testing
        );

        // Q1. Convert List to Map (Handling duplicates by keeping latest)
        Map<Long, String> idToNameMap = employees.stream()
            .collect(Collectors.toMap(
                Employee::getId,
                Employee::getName,
                (existing, replacement) -> replacement // merge function
            ));
        System.out.println("Q1 (Map): " + idToNameMap);

        // Q2. Joining Names with Delimiter
        String joinedNames = employees.stream()
            .map(Employee::getName)
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Q2 (Joined): " + joinedNames);

        // Q3. Word Frequency Counter
        String sentence = "java stream collector java collector example";
        Map<String, Long> wordFrequency = Arrays.stream(sentence.split(" "))
            .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        System.out.println("Q3 (Frequency): " + wordFrequency);

        // Q4. Department-wise Employee Count
        Map<String, Long> deptCount = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("Q4 (Dept Count): " + deptCount);

        // Q5. Partition Numbers into Even and Odd
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
            .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Q5 (Partitioned): " + partitioned);

        // Q6. Department-wise Highest Salary
        Map<String, Optional<Employee>> topSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
            ));
        System.out.println("Q6 (Top Salary Dept): " + topSalaryByDept);

        // Q7. Collecting into a TreeSet
        TreeSet<String> sortedNames = employees.stream()
            .map(Employee::getName)
            .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Q7 (Sorted Names): " + sortedNames);

        // Q8. Custom Collector – Concatenate Uppercase Strings
        String customJoined = employees.stream()
            .map(Employee::getName)
            .collect(Collector.of(
                StringBuilder::new,
                (sb, name) -> {
                    if (sb.length() > 0) sb.append("|");
                    sb.append(name.toUpperCase());
                },
                (sb1, sb2) -> sb1.append(sb2),
                StringBuilder::toString
            ));
        System.out.println("Q8 (Custom Join): " + customJoined);

        // Q9. Department-wise Average Salary
        Map<String, Double> avgSalary = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
            ));
        System.out.println("Q9 (Avg Salary): " + avgSalary);

        // Q10. Custom Collector – Product of Numbers
        long product = numbers.stream()
            .collect(Collector.of(
                () -> new long[]{1},
                (acc, n) -> acc[0] *= n,
                (acc1, acc2) -> { acc1[0] *= acc2[0]; return acc1; },
                acc -> acc[0]
            ));
        System.out.println("Q10 (Product): " + product);
    }
}
