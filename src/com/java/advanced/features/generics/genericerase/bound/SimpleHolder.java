package com.java.advanced.features.generics.genericerase.bound;

public class SimpleHolder {
    private Object obj;

    public Object get() {
        return obj;
    }

    public void set(Object obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        SimpleHolder holder = new SimpleHolder();
        holder.set("Hello");
        String str = (String) holder.get();
    }
}
