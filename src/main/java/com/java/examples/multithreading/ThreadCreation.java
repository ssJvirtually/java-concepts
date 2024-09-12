package com.java.examples.multithreading;


import java.util.concurrent.Callable;

//Thread creation extending thread class
class A extends  Thread{

    public  void run(){
        int i=1;
        while(i<10) {
            System.out.println("threadId " + Thread.currentThread().getName() +" " + i);
            i++;
        }
    }
}

//Thread creation using Runnable interface
class B implements   Runnable{

    public void run(){

        int i=1;
        while(i<10) {
            System.out.println("threadId " + Thread.currentThread().getName() +" " + i);
            i++;
        }
    }
}

//Thread creation using Callable interface
class C implements Callable<String> {


    @Override
    public String call() {
        int i=1;
        while(i<5) {
            System.out.println("threadId " + Thread.currentThread().getName() +" " + i);
            i++;
        }
        return "Called";
    }
}


public class ThreadCreation {

    public static void main(String[] args) {

        A a = new A();
        a.setName("A Thread");
        a.start();

        Thread runnableThread = new Thread(new B(),"B Thread");
        runnableThread.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //priority of threads
        System.out.println(a.getPriority());
        System.out.println(runnableThread.getPriority());

        //wait till the threads join the main thread
        try {
            a.join();
           runnableThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //thread Using lambda expression
        Thread lambdaThread = new Thread(() ->{
            System.out.println("Thread name =" + Thread.currentThread().getName());
        });
        lambdaThread.setName("lambda thread");
        lambdaThread.start();

        //System.out.println(lambdaThread);
        C c = new C();
        c.call();

        System.out.println();
    }

}
