package com.java.examples.multithreading;





public class ThreadStartMethodOverrideScen extends Thread {

    @Override
    public void start() {
     System.out.println("Sdsdsd");
     super.start();   
    }


    public static void main(String[] args) {

        ThreadStartMethodOverrideScen threadStartMethodOverrideScen = new ThreadStartMethodOverrideScen();
        threadStartMethodOverrideScen.start();

    }
}
