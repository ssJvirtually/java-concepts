package com.java.examples.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMap {


    public static void main(String[] args) {

        //create example for flatmap
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        List<Integer> numbers2 = Arrays.asList(10,20,30,40,50,60);
        List<Integer> numbers3 = Arrays.asList(100,200,300,400,500,600);

        List<List<Integer>> numbersList = Arrays.asList(numbers, numbers2, numbers3);
        numbersList.stream().flatMap(x -> x.stream()).forEach(System.out::println);




    }


}
