package com.java.examples.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import com.java.examples.model.Address;

public class EmployeeGenerator {

    // Generate a random string of given length
    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    // Generate a random integer within the given range
    private static int generateRandomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // Generate a random Date within a year range from current date
    private static Date generateRandomDate() {
        long millisInYear = 365L * 24 * 3600 * 1000;
        long millisSinceEpoch = System.currentTimeMillis();
        long randomMillisOffset = (long) (Math.random() * millisInYear);
        return new Date(millisSinceEpoch - randomMillisOffset);
    }

    // Generate a random Long number
    private static Long generateRandomLong() {
        Random random = new Random();
        return Math.abs(random.nextLong());
    }

    // Generate a random address
    private static Address generateRandomAddress() {
        Address address = new Address();
        address.houseNo = generateRandomInteger(1, 1000);
        address.area = generateRandomString(8);
        address.state = generateRandomString(10);
        address.country = generateRandomString(10);
        address.countryCode = generateRandomString(2);
        address.zipcode = generateRandomInteger(100000, 999999);
        return address;
    }

    // Generate a random contact
    private static Contact generateRandomContact() {
        Contact contact = new Contact();
        contact.type = generateRandomString(5);
        contact.number = generateRandomLong();
        return contact;
    }

    // Generate a list of random contacts
    private static List<Contact> generateRandomContacts(int count) {
        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(generateRandomContact());
        }
        return contacts;
    }

    // Generate a random employee
    private static Employee generateRandomEmployee() {
        Employee employee = new Employee();
        employee.name = generateRandomString(5);
        employee.dob = generateRandomDate();
        employee.address = generateRandomAddress();
        employee.contacts = generateRandomContacts(2); // Change the count as needed
        return employee;
    }

    // Generate the specified number of random employees
    public static List<Employee> generate(int count) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            employees.add(generateRandomEmployee());
        }
        return employees;
    }

//    public static void main(String[] args) {
//        List<Employee> employees = generate(5); // Generate 5 random employees
//        for (Employee employee : employees) {
//            System.out.println(employee.name);
//            System.out.println(employee.dob);
//            System.out.println(employee.address.houseNo + ", " + employee.address.Area + ", " +
//                    employee.address.State + ", " + employee.address.Country + ", " + employee.address.CountryCode + ", " +
//                    employee.address.zipcode);
//            for (Contact contact : employee.contacts) {
//                System.out.println(contact.type + ": " + contact.number);
//            }
//            System.out.println();
//        }
//    }
}
