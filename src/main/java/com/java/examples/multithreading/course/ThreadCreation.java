package com.java.examples.multithreading.course;

import java.util.concurrent.Callable;

class A extends Thread {


    @Override
    public void run() {
        System.out.println("Thread name = " + Thread.currentThread().getName());
    }
}


class B implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread name = " + Thread.currentThread().getName());
    }
}


public class ThreadCreation {


    public static void main(String[] args) throws InterruptedException {

        //create thread by extending thread class
        Thread threadA = new Thread(new A(), "A");

        //create thread by implementing runnable interface
        Thread threadB = new Thread(new B(), "B");


        //create thread by anonymous class of Runnable interface
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread name = " + Thread.currentThread().getName());

            }
        }, "C");


        //create thread by lambda expression
        Thread threadD = new Thread(() -> {
            System.out.println("Thread name = " + Thread.currentThread().getName());
        }, "D");



        System.out.println("Thread name = " + Thread.currentThread().getName());
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();


        Thread.sleep(1000);


    }
}
