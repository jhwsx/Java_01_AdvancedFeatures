package com.java.advanced.features.concurrent.threadsafe.staticsyncmethod;

class Ticket implements Runnable {
    private static int num = 10;
    private Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            sellTicket();
        }
    }

    private static synchronized void sellTicket() {
        if (num > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ".....sell.....Ticket#" + num--);
        }
    }
}

public class TicketDemo {
    public static void main(String[] args) {
        Ticket t = new Ticket();
        Thread window1 = new Thread(t, "Window1");
        Thread window2 = new Thread(t, "Window2");
        Thread window3 = new Thread(t, "Window3");
        Thread window4 = new Thread(t, "Window4");
        window1.start();
        window2.start();
        window3.start();
        window4.start();
    }
}
