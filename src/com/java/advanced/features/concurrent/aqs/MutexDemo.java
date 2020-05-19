package com.java.advanced.features.concurrent.aqs;

public class MutexDemo {
    private static class Ticket {
        int num = 10;
        Mutex lock = new Mutex();
        public void sellTicket() {
            lock.lock();
            try {
                if (num > 0) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " is selling ticket#" + num);
                    num--;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private static class TicketRunnable implements Runnable {
        private Ticket ticket;

        public TicketRunnable(Ticket ticket) {
            this.ticket = ticket;
        }

        @Override
        public void run() {
            while (true) {
                ticket.sellTicket();
            }
        }
    }

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        TicketRunnable ticketRunnable = new TicketRunnable(ticket);
        new Thread(ticketRunnable, "Window1").start();
        new Thread(ticketRunnable, "Window2").start();
        new Thread(ticketRunnable, "Window3").start();
        new Thread(ticketRunnable, "Window4").start();
    }
}
