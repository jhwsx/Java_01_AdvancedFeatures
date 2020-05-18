package com.java.advanced.features.concurrent.blockingqueue.delayedqueue;

/**
 * 订单的实体类
 * 包括订单号和订单的金额
 */
public class Order {
    private String id;
    private double money;

    public Order(String id, double money) {
        this.id = id;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
