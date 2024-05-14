package com.java.examples.java8;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiFunction;


@FunctionalInterface
public interface FunctionTest extends BiFunction {

    static void run() {
        System.out.println("sdf");
    }

    static void stay(){
        System.out.println("abcd");
    }

    static void stay2() {
        System.out.println("abcd");
    }
    }
