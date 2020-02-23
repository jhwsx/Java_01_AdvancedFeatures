package com.java.advanced.features.generics.genericerase.wildcard;

public class CovariantArrays {
    public static void main(String[] args) {
        Fruit[] fruits = new Apple[10];
        fruits[0] = new Apple();
        fruits[1] = new Hongfushi();
        try {
            fruits[0] = new Fruit();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            fruits[0] = new Orange();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

/*
打印结果：
java.lang.ArrayStoreException: com.java.advanced.features.generics.genericerase.wildcard.Fruit
java.lang.ArrayStoreException: com.java.advanced.features.generics.genericerase.wildcard.Orange
 */
