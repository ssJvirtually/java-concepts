package com.java.examples.multithreading.course.termination;

public class ThreadInterruptionFromOutside {


    public static void main(String[] args) {
        Computation computation = new Computation(Integer.MAX_VALUE);
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
                if(!Thread.currentThread().isInterrupted()) {
                    System.out.println("Computation = " + i);
                }
            }
        }
    }
}
