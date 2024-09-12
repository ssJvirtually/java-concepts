package com.java.examples.multithreading;

public class PrintOddEvenWithTwoThreads {

    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer();
        EvenThread evenThread = new EvenThread(printer);
        evenThread.setName("Even");
        OddThread oddThread = new OddThread(printer);
        oddThread.setName("Odd");

        evenThread.start();
        oddThread.start();

        evenThread.join();
        oddThread.join();

        System.out.println("finished");
    }

    private static class EvenThread extends Thread {
        private final Printer printer;

        EvenThread(Printer printer) {
            this.printer = printer;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                printer.printEven();
            }
        }
    }

    private static class OddThread extends Thread {
        private final Printer printer;

        OddThread(Printer printer) {
            this.printer = printer;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                printer.printOdd();
            }
        }
    }

    private static class Printer {
        private int num = 1;
        private final Object lock = new Object();

        public void printOdd() {
            synchronized (lock) {
                while (num % 2 == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(num);
                num++;
                lock.notify();
            }
        }

        public void printEven() {
            synchronized (lock) {
                while (num % 2 != 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(num);
                num++;
                lock.notify();
            }
        }
    }
}
