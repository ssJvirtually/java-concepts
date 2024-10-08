package com.java.examples.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StreamOps {

    Object minLock = new Object();

    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("qwe");
        arrayList.add("asd");
        arrayList.add("zxc");
        arrayList.add("Asdfsaf");
        arrayList.add(0,"adsfasdf");

        arrayList.stream().filter(x->x.contains("a")).forEach(x-> System.out.println(x));
        List<String> a = arrayList.stream().filter(x -> x.contains("a")).collect(Collectors.toList());



//        // Example 1: Using a lambda expression
        Supplier<Integer> messageSupplier = () -> {
        int c = 1+2;
        return c;
        };
        System.out.println(messageSupplier.get());



        // Example 2: Using a method reference
        Supplier<Double> randomDoubleSupplier = Math::random;
        System.out.println(randomDoubleSupplier.get());

        System.out.println();
    }


    public static void printEle(List<String> list){

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        Lock lock = new ReentrantLock();

        lock.lock();

    }

}
