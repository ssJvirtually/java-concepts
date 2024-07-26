package com.java.examples.polymorphism;


public class OverLoading {


    public static void main(String[] args) {


        String str = overload("str");
        overload(1);

    }


    public static String overload(String str){
        return str;
    }

    public static void overload(int i){
        System.out.println(i);
    }


}


