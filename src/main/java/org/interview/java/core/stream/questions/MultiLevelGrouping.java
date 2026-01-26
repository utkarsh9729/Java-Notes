package org.interview.java.core.stream.questions;


import org.interview.java.core.stream.questions.models.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
*
* Multi-Level Grouping Given a List<Employee> where Employee has department (String), city (String), and salary (double).

Task: Group employees first by Department, and then within each department, group them by City.

Output Type: Map<String, Map<String, List<Employee>>>.
*
* */
public class MultiLevelGrouping {

    public static void main(String[] args) {

        Employee employee = new Employee("Utkarsh","IT",1000,"Gurgoan",1);
        Employee employee1 = new Employee("Pawan","Sales",2000,"Mumbai",1);
        Employee employee2 = new Employee("Harsh","Sales",3000,"Gurgoan",1);
        Employee employee3 = new Employee("Vishal","IT",2000,"Bangalore",1);
        Employee employee4 = new Employee("John","Marketing",4000,"Bangalore",1);
        List<Employee> employeeList= Arrays.asList(employee,employee1,employee2,employee3,employee4);

        Map<String, Map<String,List<String>>> ans =
                employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.groupingBy(Employee::getCiti, Collectors.mapping(Employee::getName,Collectors.toList()))));
        System.out.println(ans);
    }
}


// employee.stream().collect(Collectors.groupingBy(Employee::getAge, Employee::getName));