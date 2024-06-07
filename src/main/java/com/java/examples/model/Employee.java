package com.java.examples.model;

import java.util.Date;
import java.util.List;

public class Employee implements Comparable<Employee> {

    String name;
    Date dob;

    Address address;


    List<Contact> contacts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public int compareTo(Employee o) {
        return this.getDob().compareTo(o.getDob());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dob=" + dob +
                ", address=" + address +
                ", contacts=" + contacts +
                '}';
    }
}
