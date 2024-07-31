package com.java.examples.multithreading.course.coordination;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadJoin {

    public static void main(String[] args) {

        List<Long> list = List.of(100L, 200L, 3200L, 4050L, 5090L);


        List<FactorialThread> factorialThreads = list.stream().map(FactorialThread::new).collect(Collectors.toList());

       //executeThreadsWithJoin(factorialThreads);

       executeThreadsWithoutJoin(factorialThreads);


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
            if(!isFactorialCalculated) {
                result = new BigInteger("1");
                for (int i = 1; i <= number; i++) {
                    result = result.multiply(BigInteger.valueOf(i));
                }
                isFactorialCalculated = true;
            }
        }
    }

    private static void executeThreadsWithJoin(List<FactorialThread> factorialThreads) {
        for(FactorialThread factorialThread : factorialThreads) {
            factorialThread.start();
        }



        for(FactorialThread factorialThread : factorialThreads) {
            try {
                factorialThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for(FactorialThread factorialThread : factorialThreads) {
            if(factorialThread.isFactorialCalculated){
                System.out.println("Factorial of " + factorialThread.number + " = " + factorialThread.result);
            }
            else{
                System.out.println("Factorial of " + factorialThread.number + " is not calculated yet");
            }
        }

    }

    private static void executeThreadsWithoutJoin(List<FactorialThread> factorialThreads) {
        for(FactorialThread factorialThread : factorialThreads) {
            factorialThread.start();
        }


        for(FactorialThread factorialThread : factorialThreads) {
            if(factorialThread.isFactorialCalculated){
                System.out.println("Factorial of " + factorialThread.number + " = " + factorialThread.result);
            }
            else{
                System.out.println("Factorial of " + factorialThread.number + " is not calculated yet");
            }
        }

    }
}
