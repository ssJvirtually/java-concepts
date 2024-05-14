package com.java.examples.java8;

import java.util.List;
import java.util.Optional;

public class Test {


    public static void main(String[] args) {


        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);

        Optional<Integer> reduce = list.stream().filter(x -> x % 2 != 0).reduce((a, b) -> a + b);

        Optional<Integer> reduce1 = list.stream().filter(x -> x % 2 == 0).reduce(Integer::sum);


        System.out.println(reduce1.get());

        System.out.println(reduce.get());

    }
}
