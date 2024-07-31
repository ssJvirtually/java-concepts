package com.java.examples.multithreading.course.coordination;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class ThreadJoinWithTimeout {
    public static void main(String[] args) {

        List<Long> list = List.of(100L, 200L, 3200L, 4050000L, 5090L);


        List<ThreadJoinWithTimeout.FactorialThread> factorialThreads = list.stream().map(ThreadJoinWithTimeout.FactorialThread::new).map(t->{
            t.setDaemon(true);
            return  t;
        }).collect(Collectors.toList());

        executeThreadsWithJoin(factorialThreads);

    }

    private static class FactorialThread extends Thread {
        private Long number;
        private BigInteger result;
        private boolean isFactorialCalculated = false;


        public FactorialThread(Long number) {
            this.number = number;
            this.setName(this.getClass().getSimpleName());
        }

        @Override
        public void run() {
            if (!isFactorialCalculated){

                result = new BigInteger("1");
                for (int i = 1; i <= number; i++) {
                    result = result.multiply(BigInteger.valueOf(i));
                }
                isFactorialCalculated = true;
            }
        }
    }

    private static void executeThreadsWithJoin(List<ThreadJoinWithTimeout.FactorialThread> factorialThreads) {
        for (ThreadJoinWithTimeout.FactorialThread factorialThread : factorialThreads) {
            factorialThread.start();
            System.out.println(factorialThread.number + " started");
        }


        for (ThreadJoinWithTimeout.FactorialThread factorialThread : factorialThreads) {
            try {
                factorialThread.join(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for (ThreadJoinWithTimeout.FactorialThread factorialThread : factorialThreads) {
            if (factorialThread.isFactorialCalculated) {
                System.out.println("Factorial of " + factorialThread.number + " = " + factorialThread.result);
            } else {
                System.out.println("Factorial of " + factorialThread.number + " is not calculated yet");
            }
        }

    }

}

