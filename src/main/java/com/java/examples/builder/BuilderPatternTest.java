package com.java.examples.builder;

public class BuilderPatternTest {


    public static void main(String[] args) {
        Employee employee = new Employee.EmployeeBuilder("name","jskr456")
                                        .age(23)
                                        .address("Address")
                                        .build();
    }
}
