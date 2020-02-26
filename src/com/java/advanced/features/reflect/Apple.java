package com.java.advanced.features.reflect;

public class Apple extends Fruit {
    private static int count;
    private final int id = count++;
    String color;
    private int size;
    public float price;

    public Apple() {
    }

    public Apple(String color, int size) {
        this.color = color;
        this.size = size;
    }

    private Apple(String color, Float price) {
        this.color = color;
        this.price = price;
    }

    Apple(String color) {
        this.color = color;
    }

    public static int getCount() {
        return count;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        checkSize(size);
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    private void checkPrice(float price) {
        if (price < 0) {
            throw new IllegalArgumentException("price is not valid, price = " + price);
        }
    }

    void checkSize(Integer size) {
        if (size > 1000) {
            throw new IllegalArgumentException("size is not valid, size = " + size);
        }
    }

    public boolean initColorAndPrice(String color, float price) {
        this.color = color;
        this.price = price;
        return true;
    }
}
