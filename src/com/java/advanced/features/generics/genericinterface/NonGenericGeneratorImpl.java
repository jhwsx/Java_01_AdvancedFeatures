package com.java.advanced.features.generics.genericinterface;

import java.util.Random;

public class NonGenericGeneratorImpl implements Generator<Integer> {
    private Random random = new Random(32);
    @Override
    public Integer next() {
        return random.nextInt(100);
    }

    public static void main(String[] args) {
        NonGenericGeneratorImpl nonGenericGenerator = new NonGenericGeneratorImpl();
        for (int i = 0; i < 5; i++) {
            System.out.println(nonGenericGenerator.next());
        }
    }
}
/*
打印结果：
77
31
85
41
39
 */