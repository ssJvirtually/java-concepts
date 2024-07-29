package com.java.examples.multithreading.course.sampleapps;

import java.util.Random;

;

public class HackerPoliceApp {

    private static final int MAX_PASSWORD = 9999;

    public static void main(String[] args) {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        new AscendingThread(vault).start();
        new DescendingThread(vault).start();
        new Police().start();
    }

    private static class Vault {
        int password;

        public Vault(int password) {
            this.password = password;
            System.out.println("Random password = " + password);
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return this.password == guess;
        }
    }


    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }
    }


    private static class AscendingThread extends HackerThread {

        public AscendingThread(Vault vault) {
            super(vault);
        }


        @Override
        public void run() {
            for (int i = 0; i < MAX_PASSWORD; i++) {
                if (vault.isCorrectPassword(i)) {
                    System.out.println(this.getName() + " guessed " + i);
                    System.exit(0);
                }
            }
        }

    }

    private static class DescendingThread extends HackerThread {

        public DescendingThread(Vault vault) {
            super(vault);
        }


        @Override
        public void run() {
            for (int i = MAX_PASSWORD; i >= 0; i--) {
                if (vault.isCorrectPassword(i)) {
                    System.out.println(this.getName() + " guessed " + i);
                    System.exit(0);
                }
            }
        }

    }

    private static class Police extends Thread {
        @Override
        public void run() {
            for (int i = 10; i >= 0; i--) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("Game over");
            System.exit(0);
        }
    }
}
