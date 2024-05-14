package com.java.examples.server;

import java.util.LinkedList;
import java.util.Queue;

class MessageQueue {
    private Queue<String> messages = new LinkedList<>();
    private int maxSize;

    public MessageQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void produce(String message) throws InterruptedException {
        while (messages.size() == maxSize) {
            wait();
        }
        messages.add(message);
        System.out.println("Produced: " + message);
        notifyAll();
    }

    public synchronized String consume() throws InterruptedException {
        while (messages.isEmpty()) {
            wait();
        }
        String message = messages.poll();
        System.out.println("Consumed: " + message);
        notifyAll();
        return message;
    }
}

class Producer implements Runnable {
    private MessageQueue queue;

    public Producer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                String message = "Message " + i;
                queue.produce(message);
                Thread.sleep(1000); // Simulate message production time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private MessageQueue queue;
    private int id;

    public Consumer(MessageQueue queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = queue.consume();
                // Simulate processing time
                Thread.sleep((long) (Math.random() * 2000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class PubSubDemo {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(5); // Set the queue size

        // Create producer
        Thread producerThread = new Thread(new Producer(queue),"producer");
        producerThread.start();

        // Create consumers
        for (int i = 1; i <= 3; i++) {
            Thread consumerThread = new Thread(new Consumer(queue, i));
            consumerThread.start();
        }
    }
}
