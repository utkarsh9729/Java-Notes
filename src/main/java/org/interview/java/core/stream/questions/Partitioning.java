package org.interview.java.core.stream.questions;


import org.interview.java.core.stream.questions.models.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.sql.JDBCType.DOUBLE;

/*
*
* Partitioning with Downstream Reduction Using the same Employee list:

Task: Partition the employees into two groups: those earning > $50k and those earning <= $50k.

Twist: Instead of a list of employees, I want the average salary of each group.

Output Type: Map<Boolean, Double>.
*
*
* */
public class Partitioning {

    public static void main(String[] args) {
        Employee employee = new Employee("Utkarsh","IT",1000,"Gurgoan",1);
        Employee employee1 = new Employee("Pawan","Sales",2000,"Mumbai",1);
        Employee employee2 = new Employee("Harsh","Sales",3000,"Gurgoan",1);
        Employee employee3 = new Employee("Vishal","IT",2000,"Bangalore",1);
        Employee employee4 = new Employee("John","Marketing",4000,"Bangalore",1);

        List<Employee> employeeList= Arrays.asList(employee,employee1,employee2,employee3,employee4);

        Map<Boolean, Double> result = employeeList.stream().collect(
                Collectors.partitioningBy(e -> e.getSalary()>2000,Collectors.averagingDouble(Employee::getSalary)));

        System.out.println(result);
    }
}
