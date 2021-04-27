package com.java.advanced.features.juc.class6.from_vector_to_queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author wangzhichao
 * @since 2021/4/25
 */
public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 1000; i++) {
            System.out.println("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    while (true) {
                        String s = tickets.poll();
                        if (s == null) {
                            break;
                        } else {
                            System.out.println("销售了" + s);
                        }

                    }
                }
            }.start();
        }
    }
}
