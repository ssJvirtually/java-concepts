package com.java.examples.recordings;

import com.java.examples.interfaces.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringReverse {

    public static void main(String[] args) {

        String s = "aabcdfdgeterererereeee";

        IntStream chars = s.chars();


        List<String> list = new ArrayList<>();

        list.add("shashi");
        list.add("shashi1");
        list.add("sha2shi");
        list.add("shas2hi");
        list.add("shash2i");
        list.add("shash23i");
        list.add("shash2i");
        list.add("shash23i");
        list.add("shash28i");


        Spliterator<String> spliterator = list.spliterator();
        Spliterator<String> stringSpliterator = spliterator.trySplit();




        list.stream().forEach(x -> System.out.println(x));




    }
}
