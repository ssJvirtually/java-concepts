package com.java.examples.multithreading.course.creation;

public class ThreadExceptionHandling {


    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.out.println("Thread name = " + Thread.currentThread().getName());
            throw new RuntimeException("Thread exception");
        });


        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("caught exception = " + e.getMessage());
            }
        });

        thread.start();


    }
}
