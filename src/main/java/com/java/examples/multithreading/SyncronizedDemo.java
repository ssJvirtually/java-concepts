package com.java.examples.multithreading;

class Counter {


    int count = 0;

    public synchronized   void incrementCount()
    {
        count++;
    }
}


public class SyncronizedDemo {

    public static void main(String[] args) throws InterruptedException {


        Counter counter = new Counter();
        System.out.println(counter.count);


        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementCount();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementCount();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println(counter.count);

    }


    
}
