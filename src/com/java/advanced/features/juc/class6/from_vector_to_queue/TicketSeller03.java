package com.java.advanced.features.juc.class6.from_vector_to_queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * 这个程序没有线程安全问题。
 * 重复销售；漏销售；超量销售。
 * @author wangzhichao
 * @since 2021/4/24
 */
public class TicketSeller03 {
    static List<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(true) {
                    synchronized (tickets) {
                        if (tickets.size() <= 0) break;
                        Thread.yield();
                        System.out.println("销售了--" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
