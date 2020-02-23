package com.java.advanced.features.generics.genericerase.bound;

public class GenericHolder<T> {
    private T obj;

    public T get() {
        return obj;
    }

    public void set(T obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        GenericHolder<String> holder = new GenericHolder<>();
        holder.set("Hello");
        String str = holder.get();
    }
}
