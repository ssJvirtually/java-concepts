package com.java.examples.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamExamples {


    public static void main(String[] args) {


        Map<String, Integer> employeeSalaries = new HashMap<>();
        employeeSalaries.put("Alice", 5000);
        employeeSalaries.put("Bob", 7000);
        employeeSalaries.put("Charlie", 4000);
        employeeSalaries.put("Dave", 6000);

        int salaryThreshold = 5000;

        List<String> highPaidEmployees = employeeSalaries.entrySet().stream()
                .filter(entry -> entry.getValue() > salaryThreshold)
                .map(entry ->entry.getKey())
                .collect(Collectors.toList());
      //  System.out.println(highPaidEmployees);



        String input = "Java Articles are Awesome";

        Character result = input.chars() // Stream of String
                .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s))) // First convert to Character object and then to lowercase
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //Store the chars in map with count
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println(result);


        System.out.println("Current Local Date: " + java.time.LocalDate.now());
        //Used LocalDate API to get the date
        System.out.println("Current Local Time: " + java.time.LocalTime.now());
        //Used LocalTime API to get the time
        System.out.println("Current Local Date and Time: " + java.time.LocalDateTime.now());
        //Used LocalDateTime API to get both date and time

        Collections.sort(highPaidEmployees,Comparator.comparing(String::length));
    }
}
