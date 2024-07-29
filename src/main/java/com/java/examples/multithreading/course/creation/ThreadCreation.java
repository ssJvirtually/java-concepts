package com.java.examples.multithreading.course.creation;

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

        Thread threadA1 = new A();
        threadA1.setName("threadA1");

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

        System.out.println("System Thread count = "+Runtime.getRuntime().availableProcessors());

        System.out.println("Thread name = " + Thread.currentThread().getName());
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        threadA1.start();


        Thread.sleep(1000);


    }
}
