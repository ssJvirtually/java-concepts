package com.java.examples.parentchild;

public class Child extends Parent {

    // Attempting to make the method private in the child class
    public void publicMethod() {
        System.out.println("This is a private method in the child class.");
    }
}
