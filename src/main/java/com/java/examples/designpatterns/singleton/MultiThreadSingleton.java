package com.java.examples.designpatterns.singleton;

class MultiThreadSingletonInstance {
    private static volatile MultiThreadSingletonInstance multiThreadSingletonInstance = null;

    private MultiThreadSingletonInstance() {}

    public static MultiThreadSingletonInstance getInstance() {
        if (multiThreadSingletonInstance == null) { // First check (no locking)
            synchronized (MultiThreadSingletonInstance.class) {
                if (multiThreadSingletonInstance == null) { // Second check (with locking)
                    System.out.println(Thread.currentThread().getName() + " entered");
                    multiThreadSingletonInstance = new MultiThreadSingletonInstance();
                }
            }
        }
        return multiThreadSingletonInstance;
    }
}

public class MultiThreadSingleton {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            MultiThreadSingletonInstance instance = MultiThreadSingletonInstance.getInstance();
            System.out.println(instance);
        });

        Thread t2 = new Thread(() -> {
            MultiThreadSingletonInstance instance = MultiThreadSingletonInstance.getInstance();
            System.out.println(instance);
        });

        Thread t3 = new Thread(() -> {
            MultiThreadSingletonInstance instance = MultiThreadSingletonInstance.getInstance();
            System.out.println(instance);
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
