package com.java.examples.multithreading.course.termination;

public class DaemonThread {

    public static void main(String[] args) {
        Computation computation = new Computation(Integer.MAX_VALUE);


        computation.setDaemon(true);
        computation.start();
        computation.interrupt();

        System.out.println(Thread.currentThread().getName() + " ended");
    }

    private static class Computation extends Thread {
        int maxLimit ;

        public Computation(int maxLimit) {
            this.maxLimit = maxLimit;
            this.setName(this.getClass().getSimpleName());
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "is started");
            for(int i = 0; i < maxLimit; i++) {

                    System.out.println("Computation = " + i);

            }
        }
    }


}
