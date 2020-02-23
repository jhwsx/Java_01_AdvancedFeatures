package com.java.advanced.features.generics.genericerase.cost;

public class EraseCost<T> {
    private final int SIZE = 10;

    public void f(Object arg) {
        // if (arg instanceof T) {} // 在 T 处编译报错：Class or array expected
        // T value = new T(); // 在第二个 T 处编译报错：Type parameter 'T' cannot be instantiated directly
        // T[] array = new T[SIZE]; // 在第二个 T 处编译报错：Type parameter 'T' cannot be instantiated directly
        T t = (T) arg; // 警告：Unchecked cast
    }
}
