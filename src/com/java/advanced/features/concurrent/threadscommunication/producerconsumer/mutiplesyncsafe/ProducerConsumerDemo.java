package com.java.advanced.features.concurrent.threadscommunication.producerconsumer.mutiplesyncsafe;

class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;
    public synchronized void produce(String name) {
        while (flag) {
            try {
                wait(); // pt2, pt1
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name + count;
        System.out.println(Thread.currentThread().getName() + "......" + this.name); // toy1
        count++;
        flag = true;
        notifyAll();
    }

    public synchronized void consume() {
        while (!flag) {
            try {
                wait(); // ct1, ct2
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "................" + this.name); // toy1
        flag = false;
        notifyAll();
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

/**
 * 多生产者，多消费者
 */
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Resource resource = new Resource();

        Thread producerThread1 = new Thread(new Producer(resource), "producerThread1");
        Thread producerThread2 = new Thread(new Producer(resource), "producerThread2");
        Thread consumerThread1 = new Thread(new Consumer(resource), "consumerThread1");
        Thread consumerThread2 = new Thread(new Consumer(resource), "consumerThread2");

        producerThread1.start();
        producerThread2.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}

/*
异常日志片段1：
producerThread2......Toy162932
producerThread1......Toy162933
consumerThread2................Toy162933
异常日志片段2：生产一次，消费两次
producerThread2......Toy107203
consumerThread1................Toy107203
consumerThread2................Toy107203
 */
