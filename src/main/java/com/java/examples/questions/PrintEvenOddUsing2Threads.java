package com.java.examples.questions;




public class PrintEvenOddUsing2Threads {

    private static final Object lock = new Object();
    private static int number = 0;
    public static void main(String[] args) {
        Thread even = new Thread(()-> {

        while (number <= 30) {
            synchronized (lock) {
                if (number % 2 == 0) {
                    System.out.println("Even :" + number);
                    number++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    },"Even");
    Thread odd = new Thread(()-> {
        while (number <= 30 ){
            synchronized (lock) {
                if (number % 2 != 0) {
                    System.out.println("odd :" + number);
                    number++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    },"Odd");
        odd.start();
        even.start();

    }
}


