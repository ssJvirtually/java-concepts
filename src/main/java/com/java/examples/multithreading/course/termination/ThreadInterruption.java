package com.java.examples.multithreading.course.termination;

public class ThreadInterruption {


    public static void main(String[] args)  {
        Thread thread = new LongTask();
        thread.start();
        thread.interrupt();
    }

    private static class LongTask extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted");
            }
        }
    }
}
