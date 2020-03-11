package com.java.advanced.features.concurrent.threadscommunication.producerconsumer.singlesafe;

class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;
    public synchronized void produce(String name) {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name + count;
        System.out.println(Thread.currentThread().getName() + "......" + this.name);
        count++;
        flag = true;
        notify();
    }

    public synchronized void consume() {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "..................." + this.name);
        flag = false;
        notify();
    }
}

class Producer implements Runnable {
    private Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        while (true) {
            resource.produce("Toy");
        }
    }
}

class Consumer implements Runnable {
    private Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            resource.consume();
        }
    }
}
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Resource resource = new Resource();
        Thread producerThread = new Thread(new Producer(resource), "producerThread");
        Thread consumerThread = new Thread(new Consumer(resource), "consumerThread");

        producerThread.start();
        consumerThread.start();
    }
}
