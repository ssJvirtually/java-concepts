package com.java.examples.streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamQuestions {


    public static void main(String[] args) {


        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);

        //create square of all odd numbers
        List<Integer> oddSqaures = list.stream().filter(e -> e % 2 != 0).map(e -> e * e).collect(Collectors.toList());


        Optional<Integer> reduce = oddSqaures.stream().reduce(Integer::sum);

        System.out.println(oddSqaures);
        System.out.println(reduce.get());

    }
}
