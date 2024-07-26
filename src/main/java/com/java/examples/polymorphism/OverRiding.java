package com.java.examples.polymorphism;

import java.util.Arrays;

class Parent{
    private void print(){
        System.out.println("Parent");
    }

    public void display(){
        print();
    }
}
class Child extends Parent{
    public void print(){
        System.out.println("Child");
    }

    public void display2(){
        System.out.println("d2:child");
    }
}

public class OverRiding {

    public static void main(String[] args){



    }
}