package com.java.examples.streams;

import java.util.Map;

public class MapStreamExamples {


    public static void main(String[] args) {


        Map<Integer,String> map = Map.of(1,"SJ,",2,"VJ",3,"RJ");



        for(Integer key :map.keySet() ){
            System.out.println("key : " + map.get(key));
        }







    }
}
