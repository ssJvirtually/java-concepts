package com.java.examples.streams;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String department;
    private int commits;

    public Employee(String name, String department, int commits) {
        this.name = name;
        this.department = department;
        this.commits = commits;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getCommits() {
        return commits;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", commits=" + commits +
                '}';
    }
}

public class StreamQuestions {


    public static void main(String[] args) {



        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);

        //create square of all odd numbers
        List<Integer> oddSqaures = list.stream().filter(e -> e % 2 != 0).map(e -> e * e).collect(Collectors.toList());


        Optional<Integer> reduce = oddSqaures.stream().reduce(Integer::sum);

//        System.out.println(oddSqaures);
//        System.out.println(reduce.get());


        //find employee with highest commits
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Engineering", 120),
                new Employee("Bob", "Engineering", 150),
                new Employee("Charlie", "HR", 85),
                new Employee("David", "HR", 75),
                new Employee("Eve", "Marketing", 90),
                new Employee("Frank", "Marketing", 110)
        );


        Map<String, Optional<Employee>> collect = employees.stream()
                .collect(
                        Collectors
                                .groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparingInt(Employee::getCommits))));

        System.out.println(collect);
    }
}
