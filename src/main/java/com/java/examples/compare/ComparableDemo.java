package com.java.examples.compare;

import com.java.examples.model.Address;
import com.java.examples.model.Contact;
import com.java.examples.model.Employee;
import com.java.examples.model.EmployeeGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComparableDemo {

    public static void main(String[] args) {


        List<Employee> employees = EmployeeGenerator.generate(3);

        employees.stream().forEach(System.out::println);


        System.out.println(employees);

        System.out.println("/n");

        //sort employees based on the attribute used to compare
        List<Employee> dob = employees.stream().sorted().collect(Collectors.toList());

        //sort employees in reverse order based on the attribute used to compare
        List<Employee> dobInreverseOrder = employees.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());


        //sort employees  based on the custom  attribute
        List<Employee> employeesByName = employees.stream().sorted(Comparator.comparing(Employee::getName)).collect(Collectors.toList());

        //sort employees in reverse based on the custom  attribute
        List<Employee> employeesByNameInReverse = employees.stream().sorted(Collections.reverseOrder(Comparator.comparing(Employee::getName))).collect(Collectors.toList());

        employees.sort(Comparator.comparing(Employee::getName));


        List<Employee> collectNat = employees.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.toList());

        List<Employee> collectrev = employees.stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).collect(Collectors.toList());

        Comparator<Employee> houseNoComparator =  Comparator.comparing(emp -> emp.getAddress().getHouseNo());

        employees.stream().sorted(houseNoComparator).collect(Collectors.toList());

        Comparator.comparing(Employee::getDob);


    }


}
