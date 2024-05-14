package com.java.examples.map;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
       HashMap<String, Integer> map = new HashMap<>();

        // First thread
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + new Date());
                map.put("key" + i, i);
            }
        },"t1");

        // Second thread
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + new Date());
                map.put("key" + i, i);
            }
        },"t2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Map size: " + map.size()); // Expected size: 2000
    }
}
