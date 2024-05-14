package com.java.examples.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortCharactersByFrequency {


    public static void main(String[] args) {


        String temp = "trrrreeAaaaAA";

        Map<Character,Integer> map = new HashMap<>();

        for(int i=0;i<temp.length();i++){
            char c = temp.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }

        Map<Integer,Boolean> tempamp = new HashMap<>();

        Queue<Integer> queue = new LinkedList<>();

        queue.addAll(new ArrayList<>());
        queue.peek();


//        String collect2 = temp.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(),
//
//
//                .counting())).entrySet().stream().sorted((k1, k2) -> k1.getValue().compareTo(k2.getValue()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new))
//                .entrySet().stream().map(entry -> String.valueOf(entry.getKey()).repeat(entry.getValue().intValue())).collect(Collectors.joining());
//        LinkedHashMap<Character, Long> collect1 = collect2;

        ArrayList<Map.Entry<Character, Integer>> collect = map.entrySet().stream()
                .sorted((k1, k2) -> k1.getValue().compareTo(k2.getValue())).collect(Collectors.toCollection(ArrayList::new));



        System.out.println("");
    }

    static class  Char implements Comparable<Char>{

        Character ch;
        Integer frequency;

        public Char(Character ch) {
            this.ch = ch;
        }

        @Override
        public int compareTo(Char o) {
            return this.frequency.compareTo(o.frequency);
        }


    }



}

