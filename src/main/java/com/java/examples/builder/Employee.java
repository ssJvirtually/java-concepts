package com.java.examples.builder;

import java.util.List;

public class Employee {
    private final String name;
    private final String email;
    private final int age;
    private final String address;
    private final String designation;
    private final List<String> qualifications;

    public Employee(Employee.EmployeeBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.address = builder.address;
        this.designation = builder.designation;
        this.qualifications = builder.qualifications;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getDesignation() {
        return designation;
    }

    public List<String> getQualifications() {
        return qualifications;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", designation='" + designation + '\'' +
                ", qualifications=" + qualifications +
                '}';
    }

    public static class EmployeeBuilder {
        private final String name;
        private final String email;
        private int age;
        private String address;
        private String designation;
        private List<String> qualifications;

        public EmployeeBuilder(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public EmployeeBuilder age(int age) {
            this.age = age;
            return this;
        }

        public EmployeeBuilder address(String address) {
            this.address = address;
            return this;
        }

        public EmployeeBuilder designation(String designation) {
            this.designation = designation;
            return this;
        }

        public EmployeeBuilder qualifications(List<String> qualifications) {
            this.qualifications = qualifications;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}