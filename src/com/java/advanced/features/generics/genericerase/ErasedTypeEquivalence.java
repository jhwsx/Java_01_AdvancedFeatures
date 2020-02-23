package com.java.advanced.features.generics.genericerase;

import java.util.ArrayList;

public class ErasedTypeEquivalence {
    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c1 == c2: " + (c1 == c2));
    }
}

/*
打印结果：
c1 = class java.util.ArrayList
c2 = class java.util.ArrayList
c1 == c2: true
 */
